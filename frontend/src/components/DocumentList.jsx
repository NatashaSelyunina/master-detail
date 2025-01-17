import { useQuery } from '@tanstack/react-query';
import api from '../services/api';

const DocumentList = () => {
    const { data: documents, isLoading, error } = useQuery(['documents'], api.getDocuments);

    if (isLoading) return <div>Loading...</div>;
    if (error) return <div>Error: {error.message}</div>;

    return (
        <div>
            <h1>Documents</h1>
            <ul>
                {documents.map(document => (
                    <li key={document.id}>
                        {document.number} - {document.totalAmount}
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default DocumentList;
