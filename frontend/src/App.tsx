import React, { useState } from 'react';
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import Layout from './components/Layout';
import LoginForm from './components/LoginForm';
import Dashboard from './components/Dashboard';
import ClientList from './components/ClientList';
import CaseDetails from './components/CaseDetails';

function App() {
  const [auth, setAuth] = useState<{ isAuthenticated: boolean; email: string; name: string }>({
    isAuthenticated: false,
    email: '',
    name: '',
  });

  const handleLogin = (email: string, name: string) => {
    setAuth({
      isAuthenticated: true,
      email,
      name,
    });
  };

  const handleLogout = () => {
    setAuth({
      isAuthenticated: false,
      email: '',
      name: '',
    });
  };

  if (!auth.isAuthenticated) {
    return <LoginForm onLogin={handleLogin} />;
  }

  return (
    <BrowserRouter>
      <Layout userName={auth.name} onLogout={handleLogout}>
        <Routes>
          <Route path="/" element={<Dashboard />} />
          <Route path="/clients" element={<ClientList />} />
          <Route path="/clients/:clientId/cases/:caseId" element={<CaseDetails />} />
          <Route path="*" element={<Navigate to="/" replace />} />
        </Routes>
      </Layout>
    </BrowserRouter>
  );
}

export default App;