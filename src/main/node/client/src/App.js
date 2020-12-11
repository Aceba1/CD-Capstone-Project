import { useState } from "react";
import FileUploader from "./components/FileUploader";

//import Dropzone from "react-dropzone";

export default function App() {

  return (
    <div className="App">
      <FileUploader chunkSizeMB={4} />
    </div>
  );
}