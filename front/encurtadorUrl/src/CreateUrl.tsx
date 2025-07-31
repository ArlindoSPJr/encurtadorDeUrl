import { useState, type FormEvent } from "react";
import './CreateUrl.css';

export default function App() {
    const [urlOriginal, setUrlOriginal] = useState<string>("");
    const [urlEncurtada, setUrlEncurtada] = useState<string>("");

    const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        try {
            const response = await fetch("http://localhost:8080/url/encurtar", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    urlOriginal
                }),
            });

            if (!response.ok) {
                throw new Error("Erro ao enviar a requisição");
            }

            const data = await response.text();
            setUrlEncurtada(data); // atribui a resposta à variável
            alert("Requisição enviada com sucesso!");
        } catch (error) {
            console.error(error);
            alert("Erro ao enviar requisição");
        }
    };


    return (
        <div className="container">
            <div className="form">
                
                <form onSubmit={handleSubmit}>
                    <h2>Encurtador de URL</h2>
                    <div className="campo">
                        <label htmlFor="urlOriginal">Url Original: </label>
                        <input
                            style={{ width: "300px" }}
                            id="origem"
                            type="text"
                            value={urlOriginal}
                            onChange={(e) => setUrlOriginal(e.target.value)}
                            required
                        />
                    </div>
                    <button type="submit"
                        style={{ width: "100px" }}>Enviar</button>
                </form>
                <form
                    onSubmit={(e) => {
                        e.preventDefault();
                        if (urlEncurtada) {
                            // Remove http:// ou https:// do início, se necessário
                            const codeSemHttp = urlEncurtada.replace(/^https?:\/\//, "");
                            window.open(`http://localhost:8080/url/${codeSemHttp}`, "_blank");
                        }
                    }}
                    style={{ marginTop: "20px" }}
                >
                    <div className="resposta">
                        <label htmlFor="urlEncurtada">Url Encurtada: </label>
                        <input
                            style={{ width: "300px" }}
                            id="urlEncurtada"
                            type="text"
                            value={urlEncurtada}
                            readOnly
                        />
                    </div>
                    <button type="submit" style={{ width: "150px" }}>Redirecionar</button>
                </form>
            </div>
        </div>
    );
}