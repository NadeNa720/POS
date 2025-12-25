# POS Admin Panel & Firebase Setup

I have prepared the code for both the **Android POS Terminal** and the **Web Admin Panel**. To make them work together and sync data, you need to connect them to a Firebase Cloud Project.

Follow these steps:

## 1. Create Firebase Project
1.  Go to [Firebase Console](https://console.firebase.google.com/).
2.  Click **"Add project"** and name it (e.g., `POS-System`).
3.  Disable Google Analytics (for simplicity) and Create Project.
4.  Once created, go to **Build -> Firestore Database** in the left menu.
5.  Click **Create Database**, select **Test mode** (for now), and choose a location (e.g., `eur3` or `us-central`).

## 2. Connect Android App
1.  In Project Overview, click the **Android** icon.
2.  **Package name**: `com.example.w`
3.  Click **Register app**.
4.  Download **`google-services.json`**.
5.  **Move this file** into your project folder:
    `AndroidStudioProjects/W/app/google-services.json`
6.  Skip the rest of the steps in the console (SDKs are already added).

## 3. Connect Web Admin Panel
1.  In Project Overview, click **Add app -> Web** (</> icon).
2.  Name it (e.g., `Admin Panel`) and Register.
3.  Copy the code inside `const firebaseConfig = { ... };`.
4.  Open the file on your computer:
    `web-admin/src/lib/firebase.ts`
5.  **Paste the keys** into the file, replacing the placeholders.

## 4. Run the Admin Panel
1.  Open a terminal in the `web-admin` folder.
2.  Run: `npm run dev`
3.  Open browser setup at `http://localhost:3000`.

## 5. Test It
1.  Run your Android App.
2.  Make a successful transaction (enter amount -> charge -> pin -> approved).
3.  Look at the Admin Panel website – the transaction should appear automatically!
