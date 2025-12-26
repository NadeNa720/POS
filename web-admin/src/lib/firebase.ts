import { initializeApp, getApps, getApp } from "firebase/app";
import { getFirestore } from "firebase/firestore";
import { getAuth } from "firebase/auth";

const firebaseConfig = {
  apiKey: "AIzaSyC6YfztQfTvThVWUE58IH0bhzPrL7gYWWU",
  authDomain: "poss-dd1b0.firebaseapp.com",
  projectId: "poss-dd1b0",
  storageBucket: "poss-dd1b0.firebasestorage.app",
  messagingSenderId: "894886080692",
  appId: "1:894886080692:web:794f59632703e7b04127e1",
  measurementId: "G-ZPXB016ZG7"
};

// Initialize Firebase only once
const app = !getApps().length ? initializeApp(firebaseConfig) : getApp();
const db = getFirestore(app);
const auth = getAuth(app);

export { db, auth };
