'use client';

import { useEffect, useState } from 'react';
import { collection, onSnapshot, query, orderBy } from 'firebase/firestore';
import { db } from '@/lib/firebase';
import { format } from 'date-fns';

interface Transaction {
  id: string;
  amount: number;
  timestamp: number;
  status: string;
}

export default function Home() {
  const [transactions, setTransactions] = useState<Transaction[]>([]);
  const [loading, setLoading] = useState(true);
  const [errorMsg, setErrorMsg] = useState("");
  const [docCount, setDocCount] = useState(-1);

  useEffect(() => {
    // Listen for real-time updates from 'transactions' collection
    // REMOVED orderBy to debug if it was an index issue
    const q = query(collection(db, "transactions"));

    const unsubscribe = onSnapshot(q, (snapshot) => {
      setDocCount(snapshot.docs.length);
      const data = snapshot.docs.map(doc => {
        const d = doc.data();
        return d as Transaction;
      });
      // Sort manually in client to avoid index requirement
      data.sort((a, b) => b.timestamp - a.timestamp);

      setTransactions(data);
      setLoading(false);
    }, (error) => {
      console.error(error);
      setErrorMsg(error.message);
      setLoading(false);
    });

    return () => unsubscribe();
  }, []);

  return (
    <div className="min-h-screen bg-gray-50 p-8 font-[family-name:var(--font-geist-sans)]">
      <main className="max-w-5xl mx-auto space-y-8">

        {/* Header */}
        <header className="flex justify-between items-center bg-white p-6 rounded-2xl shadow-sm border border-gray-100">
          <div>
            <h1 className="text-2xl font-bold text-gray-900 tracking-tight">Transactions</h1>
            <p className="text-gray-500 text-sm mt-1">Real-time monitoring</p>
          </div>
          <div className="flex items-center gap-2 px-3 py-1.5 bg-green-50 text-green-700 rounded-full text-sm font-medium animate-pulse">
            <span className="w-2 h-2 bg-green-500 rounded-full"></span>
            Live Connection
          </div>
        </header>

        {/* content */}
        <div className="bg-white rounded-2xl shadow-sm border border-gray-100 overflow-hidden">
          {loading ? (
            <div className="p-12 text-center text-gray-600 flex flex-col items-center gap-4">
              <div className="w-8 h-8 border-4 border-gray-200 border-t-blue-500 rounded-full animate-spin"></div>
              <p>Connecting to Firebase...</p>
            </div>
          ) : (
            <div className="overflow-x-auto">
              <table className="w-full text-left">
                <thead className="bg-gray-50 text-gray-500 text-xs uppercase tracking-wider border-b border-gray-100">
                  <tr>
                    <th className="px-8 py-5 font-semibold">Date & Time</th>
                    <th className="px-8 py-5 font-semibold">Transaction ID</th>
                    <th className="px-8 py-5 font-semibold">Status</th>
                    <th className="px-8 py-5 font-semibold text-right">Amount</th>
                  </tr>
                </thead>
                <tbody className="divide-y divide-gray-50">
                  {transactions.map((tx) => (
                    <tr key={tx.id} className="hover:bg-gray-50/80 transition-colors group">
                      <td className="px-8 py-5 text-sm text-gray-600">
                        {tx.timestamp ? format(tx.timestamp, 'MMM d, yyyy • HH:mm:ss') : 'N/A'}
                      </td>
                      <td className="px-8 py-5 text-sm font-mono text-gray-400 group-hover:text-gray-600 transition-colors text-xs">
                        {tx.id}
                      </td>
                      <td className="px-8 py-5 text-sm">
                        <span className={`px-2.5 py-1 rounded-full text-xs font-medium border ${tx.status === 'SUCCESS'
                          ? 'bg-green-50 text-green-700 border-green-100'
                          : 'bg-yellow-50 text-yellow-700 border-yellow-100'
                          }`}>
                          {tx.status}
                        </span>
                      </td>
                      <td className="px-8 py-5 text-sm font-bold text-gray-900 text-right font-mono">
                        ${(tx.amount / 100).toFixed(2)}
                      </td>
                    </tr>
                  ))}
                  {transactions.length === 0 && (
                    <tr>
                      <td colSpan={4} className="px-8 py-16 text-center">
                        <div className="flex flex-col items-center gap-2 text-gray-400">
                          <svg className="w-12 h-12 text-gray-200" fill="none" stroke="currentColor" viewBox="0 0 24 24"><path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M9 5H7a2 2 0 00-2 2v12a2 2 0 002 2h10a2 2 0 002-2V7a2 2 0 00-2-2h-2M9 5a2 2 0 002 2h2a2 2 0 002-2M9 5a2 2 0 012-2h2a2 2 0 012 2m-3 7h3m-3 4h3m-6-4h.01M9 16h.01"></path></svg>
                          <p className="font-medium text-gray-500">No transactions recorded</p>
                          <p className="text-sm">Make a charge on the POS terminal to see it here.</p>
                        </div>
                      </td>
                    </tr>
                  )}
                </tbody>
              </table>
            </div>
          )}
        </div>
      </main>
    </div>
  );
}
