import axios from 'axios';

const api = axios.create({
    baseURL: 'http://localhost:8080/api',
});

export const getDocuments = async () => {
    const response = await api.get('/document/all');
    return response.data;
};

export const createDocument = async (document) => {
    const response = await api.post('/docs/document', document);
    return response.data;
};

export const createSpecification = async (specification, id) => {
    const response = await api.post(`/docs/specification/${id}`, specification);
    return response.data;
};

export const updateDocument = async (document) => {
    const response = await api.put('/document', document);
    return response.data;
};

export const deleteDocument = async (id) => {
    const response = await api.delete(`/document/${id}`);
    return response.data;
};

export const updateSpecification = async (specification) => {
    const response = await api.put('/docs/specification', specification);
    return response.data;
};

export const deleteSpecification = async (id) => {
    const response = await api.delete(`/docs/specification/${id}`);
    return response.data;
};

export default api;