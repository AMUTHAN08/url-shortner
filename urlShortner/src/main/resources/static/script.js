const API_BASE = "/rest/v1";

async function shortenUrl() {
    const longUrl = document.getElementById("longUrl").value;

    if (!longUrl) {
        alert("Please enter a URL");
        return;
    }

    const response = await fetch(`${API_BASE}/shortner`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ originalUrl: longUrl })
    });

    const text = await response.text();

    document.getElementById("shortLink").value = text;
    document.getElementById("resultBox").classList.remove("hidden");
}

function copyLink() {
    const input = document.getElementById("shortLink");
    navigator.clipboard.writeText(input.value);
}
