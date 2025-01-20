import React, { useState } from 'react';
import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query';
import { getDocuments, createDocument, updateDocument, deleteDocument, createSpecification, updateSpecification, deleteSpecification } from '../services/api';

const DocumentList = () => {
    const queryClient = useQueryClient();

    // Исправленный useQuery
    const { data: documents, isLoading, error } = useQuery({
        queryKey: ['documents'],
        queryFn: getDocuments, // Передаем функцию, а не результат вызова
    });

    const [newDocument, setNewDocument] = useState({ number: '', totalAmount: '' });
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
        setNewDocument({ number: '', totalAmount: '' });
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
            <table>
                <thead>
                    <tr>
                        <th>Номер документа</th>
                        <th>Общая сумма</th>
                        <th>Действия</th>
                    </tr>
                </thead>
                <tbody>
                    {documents?.map(document => (
                        <tr key={document.id}>
                            <td>
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
                            <td>
                                {editingDocument?.id === document.id ? (
                                    <input
                                        type="text"
                                        value={editingDocument.totalAmount}
                                        onChange={(e) => setEditingDocument({ ...editingDocument, totalAmount: e.target.value })}
                                    />
                                ) : (
                                    document.totalAmount
                                )}
                            </td>
                            <td>
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
                    value={newDocument.totalAmount}
                    onChange={(e) => setNewDocument({ ...newDocument, totalAmount: e.target.value })}
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