import React, { useState } from 'react';
import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query';
import { getDocuments, createDocument, updateDocument, deleteDocument, createSpecification, updateSpecification, deleteSpecification } from '../services/api';
const DocumentList = () => {
    const queryClient = useQueryClient();

    const { data: documents, isLoading, error } = useQuery({
        queryKey: ['documents'],
        queryFn: getDocuments,
    });

    const [newDocument, setNewDocument] = useState({ number: '', sum: '', date: '', note: '' });
    const [newSpecification, setNewSpecification] = useState({ documentId: '', title: '', sum: '' });
    const [editingDocument, setEditingDocument] = useState(null);
    const [editingSpecification, setEditingSpecification] = useState(null);

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

    const handleAddDocument = () => {
        createDocumentMutation.mutate(newDocument);
        setNewDocument({ number: '', sum: '', date: '', note: '' });
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

    const handleAddSpecification = (documentId) => {
        createSpecificationMutation.mutate({ ...newSpecification, documentId });
        setNewSpecification({ documentId: '', title: '', sum: '' });
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
        <table style={{ borderCollapse: 'collapse', width: '100%', border: '1px solid black' }}>
            <thead>
                <tr>
                    <th style={{ border: '1px solid black', padding: '8px' }}>Номер документа</th>
                    <th style={{ border: '1px solid black', padding: '8px' }}>Общая сумма</th>
                    <th style={{ border: '1px solid black', padding: '8px' }}>Дата</th>
                    <th style={{ border: '1px solid black', padding: '8px' }}>Примечания</th>
                    <th style={{ border: '1px solid black', padding: '8px' }}>Действия</th>
                </tr>
            </thead>
            <tbody>
                {documents?.map(document => (
                    <React.Fragment key={document.id}>
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
                            <td style={{ border: '1px solid black', padding: '8px' }}>
                                {editingDocument?.id === document.id ? (
                                    <button onClick={handleUpdateDocument}>Сохранить</button>
                                ) : (
                                    <button onClick={() => handleEditDocument(document)}>Изменить</button>
                                )}
                                <button onClick={() => handleDeleteDocument(document.id)}>Удалить</button>
                                <button onClick={() => setNewSpecification({ ...newSpecification, documentId: document.id })}>
                                    Добавить спецификацию
                                </button>
                            </td>
                        </tr>
                        {document.specifications?.length > 0 && (
                            <tr>
                                <td colSpan="5" style={{ padding: '0' }}>
                                    <table style={{ width: '100%', borderCollapse: 'collapse', border: '1px solid black' }}>
                                        <thead>
                                            <tr>
                                                <th style={{ border: '1px solid black', padding: '8px' }}>Название спецификации</th>
                                                <th style={{ border: '1px solid black', padding: '8px' }}>Сумма</th>
                                                <th style={{ border: '1px solid black', padding: '8px' }}>Действия</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            {document.specifications.map(specification => (
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
                                                    <td style={{ border: '1px solid black', padding: '8px' }}>
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
                    </React.Fragment>
                ))}
            </tbody>
        </table>
            <div>
            <input
                    type="text"
                    placeholder="Номер документа"
                    value={newDocument.number}
                    onChange={(e) => setNewDocument({ ...newDocument, number: e.target.value })}
                />
                <input
                    type="text"
                    placeholder="Общая сумма"
                    value={newDocument.sum}
                    onChange={(e) => setNewDocument({ ...newDocument, sum: e.target.value })}
                />
                <input
                    type="date"
                    placeholder="Дата"
                    value={newDocument.date}
                    onChange={(e) => setNewDocument({ ...newDocument, date: e.target.value })}
                />
                <input
                    type="text"
                    placeholder="Примечания"
                    value={newDocument.note}
                    onChange={(e) => setNewDocument({ ...newDocument, note: e.target.value })}
                />
                <button onClick={handleAddDocument}>Добавить документ</button>
            </div>
            {newSpecification.documentId && (
                <div>
                    <input
                        type="text"
                        placeholder="Название спецификации"
                        value={newSpecification.title}
                        onChange={(e) => setNewSpecification({ ...newSpecification, title: e.target.value })}
                    />
                    <input
                        type="text"
                        placeholder="Сумма"
                        value={newSpecification.sum}
                        onChange={(e) => setNewSpecification({ ...newSpecification, sum: e.target.value })}
                    />
                    <button onClick={() => handleAddSpecification(newSpecification.documentId)}>Добавить спецификацию</button>
                </div>
            )}
            {editingSpecification && (
                <div>
                    <input
                        type="text"
                        placeholder="Название спецификации"
                        value={editingSpecification.title}
                        onChange={(e) => setEditingSpecification({ ...editingSpecification, title: e.target.value })}
                    />
                    <input
                        type="text"
                        placeholder="Сумма"
                        value={editingSpecification.sum}
                        onChange={(e) => setEditingSpecification({ ...editingSpecification, sum: e.target.value })}
                    />
                    <button onClick={handleUpdateSpecification}>Сохранить</button>
                </div>
            )}
        </div>
    );
};

export default DocumentList;