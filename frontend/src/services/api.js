import axios from 'axios';

const api = axios.create({
    baseURL: 'http://localhost:8080/api',
});

export const getDocuments = async () => {
    const response = await api.get('/document');
    return response.data;
};

export const createDocument = async (document) => {
    const response = await api.post('/document', document);
    return response.data;
};

export default api;
