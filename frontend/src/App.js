import React, { useState } from 'react';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import DocumentList from './components/DocumentList';

const queryClient = new QueryClient();

const App = () => {
    const [showDocuments, setShowDocuments] = useState(false);

    return (
        <QueryClientProvider client={queryClient}>
            <div className="App">
                <button onClick={() => setShowDocuments(!showDocuments)}>
                    {showDocuments ? 'Скрыть документы' : 'Посмотреть все документы'}
                </button>
                {showDocuments && <DocumentList />}
            </div>
        </QueryClientProvider>
    );
};

export default App;