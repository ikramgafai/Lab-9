import React, { useState } from 'react';
import CompteList from './components/CompteList';
import CompteForm from './components/CompteForm';

function App() {
  const [refreshKey, setRefreshKey] = useState(0);

  const handleAddSuccess = () => {
    setRefreshKey(prev => prev + 1);
  };

  return (
    <div>
      <CompteForm onAddSuccess={handleAddSuccess} />
      <CompteList key={refreshKey} />
    </div>
  );
}

export default App;