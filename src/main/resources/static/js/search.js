document.addEventListener('DOMContentLoaded', function () {
    const searchForm = document.getElementById('searchForm');

    searchForm.addEventListener('submit', function (e) {
        e.preventDefault();
        const ticker = searchForm.search.value.trim();
        if (ticker) {
            // Updated API endpoint to match the RESTful convention
            const apiUrl = `http://localhost:8080/api/ticker-details/${ticker}`;

            fetch(apiUrl)
                .then(response => {
                    if (!response.ok) {
                        throw new Error(`HTTP error! status: ${response.status}`);
                    }
                    return response.json();
                })
                .then(data => displayResults(data))
                .catch(error => {
                    console.log(error);
                    document.getElementById('results').innerHTML = `<p>Error loading data!</p>`;
                });
        }
    });

    function displayResults(data) {
        const resultsContainer = document.getElementById('results');
        // Update this to match the data structure returned by your API
        resultsContainer.innerHTML = `
            <h3>Results for: ${data.ticker}</h3>
            <p>Name: ${data.name}</p>
            <p>Market: ${data.market}</p>
            <p>Locale: ${data.locale}</p>
            <p>Primary Exchange: ${data.primaryExchange}</p>
            <p>Active: ${data.active}</p>
            <p>Currency Name: ${data.currencyName}</p>
            <p>CIK: ${data.cik}</p>
            <p>Market Cap: ${data.marketCap}</p>
            <p>Phone Number: ${data.phoneNumber}</p>
            <p>Address: ${data.address}</p>
            <p>Description: ${data.description}</p>
            <!-- ... Additional fields as needed -->
        `;
    }
});