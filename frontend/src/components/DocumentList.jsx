import React, { useState } from 'react';
import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query';
import {
    getDocuments,
    createDocument,
    updateDocument,
    deleteDocument,
    createSpecification,
    updateSpecification,
    deleteSpecification,
} from '../services/api';

const DocumentList = () => {
    const queryClient = useQueryClient();

    const { data: documents, isLoading, error } = useQuery({
        queryKey: ['documents'],
        queryFn: getDocuments,
    });
    console.log(documents);

    const [newDocument, setNewDocument] = useState({ number: '', sum: '', date: '', note: '' });
    const [newDocumentSpecifications, setNewDocumentSpecifications] = useState([]);
    const [newSpecification, setNewSpecification] = useState({ title: '', sum: '' });
    const [editingDocument, setEditingDocument] = useState(null);
    const [editingSpecification, setEditingSpecification] = useState(null);
    const [addingSpecificationForDocumentId, setAddingSpecificationForDocumentId] = useState(null);

    const createDocumentMutation = useMutation({
        mutationFn: createDocument,
        onSuccess: () => {
            queryClient.invalidateQueries({ queryKey: ['documents'] });
        },
    });

    const updateDocumentMutation = useMutation({
        mutationFn: updateDocument,
        onSuccess: () => {
            queryClient.invalidateQueries({ queryKey: ['documents'] });
        },
    });

    const deleteDocumentMutation = useMutation({
        mutationFn: deleteDocument,
        onSuccess: () => {
            queryClient.invalidateQueries({ queryKey: ['documents'] });
        },
    });

    const createSpecificationMutation = useMutation({
        mutationFn: createSpecification,
        onSuccess: () => {
            queryClient.invalidateQueries({ queryKey: ['documents'] });
        },
    });

    const updateSpecificationMutation = useMutation({
        mutationFn: updateSpecification,
        onSuccess: () => {
            queryClient.invalidateQueries({ queryKey: ['documents'] });
        },
    });

    const deleteSpecificationMutation = useMutation({
        mutationFn: deleteSpecification,
        onSuccess: () => {
            queryClient.invalidateQueries({ queryKey: ['documents'] });
        },
    });

    const handleAddDocumentWithSpecifications = () => {
        const documentWithSpecifications = {
            ...newDocument,
            specifications: newDocumentSpecifications,
        };
        createDocumentMutation.mutate(documentWithSpecifications, {
            onSuccess: () => {
                setNewDocument({ number: '', sum: '', date: '', note: '' });
                setNewDocumentSpecifications([]);
            },
        });
    };

    const handleAddSpecificationToNewDocument = () => {
        if (newSpecification.title && newSpecification.sum) {
            setNewDocumentSpecifications([...newDocumentSpecifications, newSpecification]);
            setNewSpecification({ title: '', sum: '' });
        }
    };

    const handleEditDocument = (document) => {
        setEditingDocument(document);
    };

    const handleUpdateDocument = () => {
        updateDocumentMutation.mutate(editingDocument);
        setEditingDocument(null);
    };

    const handleDeleteDocument = (id) => {
        deleteDocumentMutation.mutate(id);
    };

    const handleSaveSpecification = (documentId) => {
        console.log('Document ID:', documentId);
        const id = Number(documentId);
    
        if (isNaN(id)) {
            console.error('Invalid documentId:', documentId);
            return;
        }
    
        console.log('Data before mutation:', { ...newSpecification, documentId: id });
        createSpecificationMutation.mutate(
            { ...newSpecification, documentId: id },
            {
                onSuccess: () => {
                    setNewSpecification({ title: '', sum: '' });
                    setAddingSpecificationForDocumentId(null);
                },
                onError: (error) => {
                    console.error('Ошибка при добавлении спецификации:', error);
                    alert('Произошла ошибка при добавлении спецификации.');
                },
            }
        );
    };

    const handleEditSpecification = (specification) => {
        setEditingSpecification(specification);
    };

    const handleUpdateSpecification = () => {
        updateSpecificationMutation.mutate(editingSpecification);
        setEditingSpecification(null);
    };

    const handleDeleteSpecification = (id) => {
        deleteSpecificationMutation.mutate(id);
    };

    if (isLoading) return <div>Loading...</div>;
    if (error) return <div>Error: {error.message}</div>;

    return (
        <div>
            <h1>Documents</h1>
            <div style={{ border: '1px solid #ccc', borderRadius: '8px', padding: '16px', marginBottom: '20px', backgroundColor: '#f9f9f9' }}>
                <h2 style={{ marginTop: 0 }}>Форма добавления документа</h2>
                <div>
                    <h3>Данные документа</h3>
                    <div style={{ marginBottom: '16px' }}>
                        <input
                            type="text"
                            placeholder="Номер документа"
                            value={newDocument.number}
                            onChange={(e) => setNewDocument({ ...newDocument, number: e.target.value })}
                            style={{ marginRight: '8px', padding: '8px', width: '200px' }}
                        />
                        <input
                            type="text"
                            placeholder="Общая сумма"
                            value={newDocument.sum}
                            onChange={(e) => setNewDocument({ ...newDocument, sum: e.target.value })}
                            style={{ marginRight: '8px', padding: '8px', width: '200px' }}
                        />
                        <input
                            type="date"
                            placeholder="Дата"
                            value={newDocument.date}
                            onChange={(e) => setNewDocument({ ...newDocument, date: e.target.value })}
                            style={{ marginRight: '8px', padding: '8px' }}
                        />
                        <input
                            type="text"
                            placeholder="Примечания"
                            value={newDocument.note}
                            onChange={(e) => setNewDocument({ ...newDocument, note: e.target.value })}
                            style={{ marginRight: '8px', padding: '8px', width: '200px' }}
                        />
                    </div>
                </div>
                <div>
                    <h3>Спецификации документа</h3>
                    <div style={{ marginBottom: '16px' }}>
                        <input
                            type="text"
                            placeholder="Название спецификации"
                            value={newSpecification.title}
                            onChange={(e) => setNewSpecification({ ...newSpecification, title: e.target.value })}
                            style={{ marginRight: '8px', padding: '8px', width: '200px' }}
                        />
                        <input
                            type="text"
                            placeholder="Сумма"
                            value={newSpecification.sum}
                            onChange={(e) => setNewSpecification({ ...newSpecification, sum: e.target.value })}
                            style={{ marginRight: '8px', padding: '8px', width: '200px' }}
                        />
                        <button onClick={handleAddSpecificationToNewDocument} style={{ padding: '8px 16px', backgroundColor: '#007bff', color: '#fff', border: 'none', borderRadius: '4px' }}>
                            Добавить спецификацию
                        </button>
                    </div>
                    <div>
                        <h4>Список спецификаций</h4>
                        <ul style={{ listStyleType: 'none', padding: 0 }}>
                            {newDocumentSpecifications.map((spec, index) => (
                                <li key={index} style={{ marginBottom: '8px' }}>
                                    {spec.title} - {spec.sum}
                                </li>
                            ))}
                        </ul>
                    </div>
                </div>
                <button onClick={handleAddDocumentWithSpecifications} style={{ padding: '8px 16px', backgroundColor: '#28a745', color: '#fff', border: 'none', borderRadius: '4px' }}>
                    Сохранить документ со спецификациями
                </button>
            </div>
            <table style={{ borderCollapse: 'collapse', width: '100%', border: '1px solid black', marginTop: '20px' }}>
                <thead>
                    <tr>
                        <th style={{ border: '1px solid black', padding: '8px' }}>Номер документа</th>
                        <th style={{ border: '1px solid black', padding: '8px' }}>Общая сумма</th>
                        <th style={{ border: '1px solid black', padding: '8px' }}>Дата</th>
                        <th style={{ border: '1px solid black', padding: '8px' }}>Примечания</th>
                        <th style={{ border: '1px solid black', padding: '8px', width: '200px' }}>Действия</th>
                    </tr>
                </thead>
                <tbody>
                    {documents?.map((document) => (
                        <React.Fragment key={document.id}>
                            <tr>
                                <td colSpan="5" style={{ border: '1px solid black', padding: '8px', backgroundColor: '#f0f0f0' }}>
                                    <strong>Номер документа:</strong> {document.number}
                                </td>
                            </tr>
                            <tr style={{ border: '1px solid black' }}>
                                <td style={{ border: '1px solid black', padding: '8px' }}>
                                    {editingDocument?.id === document.id ? (
                                        <input
                                            type="text"
                                            value={editingDocument.number}
                                            onChange={(e) => setEditingDocument({ ...editingDocument, number: e.target.value })}
                                        />
                                    ) : (
                                        document.number
                                    )}
                                </td>
                                <td style={{ border: '1px solid black', padding: '8px' }}>
                                    {editingDocument?.id === document.id ? (
                                        <input
                                            type="text"
                                            value={editingDocument.sum}
                                            onChange={(e) => setEditingDocument({ ...editingDocument, sum: e.target.value })}
                                        />
                                    ) : (
                                        document.sum
                                    )}
                                </td>
                                <td style={{ border: '1px solid black', padding: '8px' }}>
                                    {editingDocument?.id === document.id ? (
                                        <input
                                            type="date"
                                            value={editingDocument.date}
                                            onChange={(e) => setEditingDocument({ ...editingDocument, date: e.target.value })}
                                        />
                                    ) : (
                                        document.date
                                    )}
                                </td>
                                <td style={{ border: '1px solid black', padding: '8px' }}>
                                    {editingDocument?.id === document.id ? (
                                        <input
                                            type="text"
                                            value={editingDocument.note}
                                            onChange={(e) => setEditingDocument({ ...editingDocument, note: e.target.value })}
                                        />
                                    ) : (
                                        document.note
                                    )}
                                </td>
                                <td style={{ border: '1px solid black', padding: '8px', width: '200px' }}>
                                    {editingDocument?.id === document.id ? (
                                        <button onClick={handleUpdateDocument}>Сохранить</button>
                                    ) : (
                                        <button onClick={() => handleEditDocument(document)}>Изменить</button>
                                    )}
                                    <button onClick={() => handleDeleteDocument(document.id)}>Удалить</button>
                                </td>
                            </tr>
                            {addingSpecificationForDocumentId === document.id && (
                                <tr>
                                    <td colSpan="5" style={{ padding: '16px', backgroundColor: '#f9f9f9' }}>
                                        <div>
                                            <h4>Добавить спецификацию</h4>
                                                <input
                                                    type="text"
                                                    placeholder="Название спецификации"
                                                    value={newSpecification.title}
                                                    onChange={(e) => setNewSpecification({ ...newSpecification, title: e.target.value })}
                                                    style={{ marginRight: '8px', padding: '8px', width: '200px' }}
                                                />
                                                <input
                                                    type="text"
                                                    placeholder="Сумма"
                                                    value={newSpecification.sum}
                                                    onChange={(e) => setNewSpecification({ ...newSpecification, sum: e.target.value })}
                                                    style={{ marginRight: '8px', padding: '8px', width: '200px' }}
                                                />
                                                <button onClick={() => handleSaveSpecification(document.id)} style={{ padding: '8px 16px', backgroundColor: '#007bff', color: '#fff', border: 'none', borderRadius: '4px' }}>
                                                    Сохранить спецификацию
                                                </button>
                                        </div>
                                    </td>
                                </tr>
                            )}
                            {document.specifications?.length > 0 && (
                                <tr>
                                    <td colSpan="5" style={{ padding: '0' }}>
                                        <table style={{ width: '100%', borderCollapse: 'collapse', border: '1px solid black' }}>
                                            <thead>
                                                <tr>
                                                    <th style={{ border: '1px solid black', padding: '8px' }}>Название спецификации</th>
                                                    <th style={{ border: '1px solid black', padding: '8px' }}>Сумма</th>
                                                    <th style={{ border: '1px solid black', padding: '8px', width: '200px' }}>Действия</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                {document.specifications.map((specification) => (
                                                    <tr key={specification.id} style={{ border: '1px solid black' }}>
                                                        <td style={{ border: '1px solid black', padding: '8px' }}>
                                                            {editingSpecification?.id === specification.id ? (
                                                                <input
                                                                    type="text"
                                                                    value={editingSpecification.title}
                                                                    onChange={(e) => setEditingSpecification({ ...editingSpecification, title: e.target.value })}
                                                                />
                                                            ) : (
                                                                specification.title
                                                            )}
                                                        </td>
                                                        <td style={{ border: '1px solid black', padding: '8px' }}>
                                                            {editingSpecification?.id === specification.id ? (
                                                                <input
                                                                    type="text"
                                                                    value={editingSpecification.sum}
                                                                    onChange={(e) => setEditingSpecification({ ...editingSpecification, sum: e.target.value })}
                                                                />
                                                            ) : (
                                                                specification.sum
                                                            )}
                                                        </td>
                                                        <td style={{ border: '1px solid black', padding: '8px', width: '200px' }}>
                                                            {editingSpecification?.id === specification.id ? (
                                                                <button onClick={handleUpdateSpecification}>Сохранить</button>
                                                            ) : (
                                                                <button onClick={() => handleEditSpecification(specification)}>Изменить</button>
                                                            )}
                                                            <button onClick={() => handleDeleteSpecification(specification.id)}>Удалить</button>
                                                        </td>
                                                    </tr>
                                                ))}
                                            </tbody>
                                        </table>
                                    </td>
                                </tr>
                            )}
                            <tr>
                                <td colSpan="5" style={{ padding: '16px 0', borderBottom: '2px solid #ccc' }}></td>
                            </tr>
                        </React.Fragment>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default DocumentList;