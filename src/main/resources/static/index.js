const mp = new MercadoPago('TEST-77b30600-80c4-4fe2-a54b-2ff4e92d91b2',{
    locale:'es-AR'
});

const MP = async () => {
    try {
        const miArticulo = {
            title: "Paquete de CP x10",
            quantity: 1,
            unit_price: 10000
        };

        const response = await fetch("api/mp", {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(miArticulo)
        });

        if (response.ok) {
            const preference = await response.text();
            createCheckoutButton(preference);
            console.log("Preferencia: " + preference);
        } else {
            throw new Error('Failed to create preference. Status: ' + response.status);
        }
    } catch (e) {
        alert("Error: " + e.message);
        console.error("Error: ", e);
    }
};

const createCheckoutButton = (preferenceId_OK) => {
    const bricksBuilder = mp.bricks();
    const generateButton = async () => {
        if (window.checkoutButton) window.checkoutButton.unmount();
        bricksBuilder.create("wallet", "wallet_container", {
            initialization: {
                preferenceId: preferenceId_OK,
            },
        });
    };
    generateButton();
};
