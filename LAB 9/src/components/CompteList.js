// CompteList.jsx
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import API_BASE_URL from '../config';

function CompteList() {
    const [comptes, setComptes] = useState([]);
    const [loading, setLoading] = useState(true);

    const fetchComptes = async () => {
        setLoading(true);
        try {
            const res = await axios.get(`${API_BASE_URL}/comptes`);
            setComptes(res.data);
        } catch (err) {
            console.error(err);
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchComptes();
    }, []);

    // Format date: 2025-11-03 → 03/11/2025
    const formatDate = (dateStr) => {
        if (!dateStr) return '';
        const d = new Date(dateStr);
        return `${String(d.getDate()).padStart(2, '0')}/${String(d.getMonth() + 1).padStart(2, '0')}/${d.getFullYear()}`;
    };

    // Format money: 7000 → 7 000
    const formatMoney = (num) => {
        return Number(num).toLocaleString('fr-FR');
    };

    return (
        <div className="list-card">
            <h2>Liste des Comptes</h2>

            {loading ? (
                <div className="loader">
                    <div className="spinner-large"></div>
                    <p>Chargement des comptes...</p>
                </div>
            ) : (
                <div className="table-container">
                    <table>
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Solde</th>
                                <th>Date de Création</th>
                                <th>Type</th>
                            </tr>
                        </thead>
                        <tbody>
                            {comptes.length === 0 ? (
                                <tr>
                                    <td colSpan="4" className="empty">
                                        Aucun compte disponible
                                    </td>
                                </tr>
                            ) : (
                                comptes.map(compte => (
                                    <tr key={compte.id}>
                                        <td><strong>#{compte.id}</strong></td>
                                        <td className="money">{formatMoney(compte.solde)}</td>
                                        <td>{formatDate(compte.dateCreation)}</td>
                                        <td>
                                            <span className={`badge ${compte.type.toLowerCase()}`}>
                                                {compte.type}
                                            </span>
                                        </td>
                                    </tr>
                                ))
                            )}
                        </tbody>
                    </table>
                </div>
            )}

            <style jsx>{`
                .list-card {
                    max-width: 900px;
                    margin: 2rem auto;
                    background: white;
                    border-radius: 16px;
                    box-shadow: 0 10px 30px rgba(0,0,0,0.08);
                    overflow: hidden;
                    font-family: 'Segoe UI', sans-serif;
                }
                h2 {
                    background: #2c3e50;
                    color: white;
                    padding: 1.2rem 1.5rem;
                    margin: 0;
                    font-size: 1.5rem;
                }
                .loader {
                    text-align: center;
                    padding: 3rem 1rem;
                    color: #7f8c8d;
                }
                .spinner-large {
                    width: 40px;
                    height: 40px;
                    border: 4px solid #f0f0f0;
                    border-top-color: #3498db;
                    border-radius: 50%;
                    animation: spin 1s linear infinite;
                    margin: 0 auto 1rem;
                }
                @keyframes spin {
                    to { transform: rotate(360deg); }
                }
                .table-container {
                    overflow-x: auto;
                }
                table {
                    width: 100%;
                    border-collapse: collapse;
                }
                th {
                    background: #f8f9fa;
                    padding: 1rem;
                    text-align: left;
                    font-weight: 600;
                    color: #2c3e50;
                    font-size: 0.95rem;
                    border-bottom: 2px solid #dee2e6;
                }
                td {
                    padding: 1rem;
                    border-bottom: 1px solid #eee;
                }
                tr:nth-child(even) td {
                    background: #f9fbfc;
                }
                tr:hover td {
                    background: #eef5ff;
                    transition: background 0.2s;
                }
                .empty {
                    text-align: center;
                    color: #95a5a6;
                    font-style: italic;
                    padding: 2rem !important;
                }
                .money {
                    font-weight: 600;
                    color: #27ae60;
                }
                .badge {
                    padding: 0.35rem 0.8rem;
                    border-radius: 1.2rem;
                    font-size: 0.8rem;
                    font-weight: 600;
                    text-transform: uppercase;
                }
                .badge.courant {
                    background: #3498db;
                    color: white;
                }
                .badge.epargne {
                    background: #27ae60;
                    color: white;
                }
            `}</style>
        </div>
    );
}

export default CompteList;