const API_BASE = "/rest/v1";

async function shortenUrl() {
    const longUrl = document.getElementById("longUrl").value;

    const response = await fetch(`${API_BASE}/shortner`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ originalUrl: longUrl })
    });

    const text = await response.text();
    document.getElementById("shortResult").innerHTML =
        `Short URL: <a href="${text}" target="_blank">${text}</a>`;
}

async function getStats() {
    const code = document.getElementById("code").value;

    const response = await fetch(`${API_BASE}/analytics/${code}`);
    const data = await response.json();

    document.getElementById("stats").innerHTML =
        `Total Clicks: ${data.totalClicks} <br> Unique Users: ${data.uniqueUsers}`;
}
