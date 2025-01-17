import React from 'react';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import DocumentList from './components/DocumentList';

const queryClient = new QueryClient();

const App = () => {
    return (
        <QueryClientProvider client={queryClient}>
            <div className="App">
                <DocumentList />
            </div>
        </QueryClientProvider>
    );
};

export default App;
