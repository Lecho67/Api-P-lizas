const mp=new MercadoPago('TEST-77b30600-80c4-4fe2-a54b-2ff4e92d91b2',{
    locale:'es-CO'
});
const MP= async()=>{
    try{
    miArticulo={}
    miArticulo.title="Paquete de CP x10";
    miArticulo.quantity=1;
    miArticulo.unit_price=10000;

    const response= await fetch("api/mp",{
    method:'POST',
    headers:{
    'Accept':'Application/json',
    'Content-Type':'Application/json'
    },
    body: JSON.stringify(miArticulo)
    })
    const preference = await response.text()
    createCheckoutButton(preference)
    console.log("Preferencia"+preference)
    }catch(e){alert("error: "+e)}
    }

    const createCheckoutButton=(preferenceId_OK)=>{
    const bricksBuilder= mp.bricks();
    const generateButton=async()=>{
        if(window.checkoutButton) window.checkoutButton.unmount()
   bricksBuilder.create("wallet","wallet_container",{
   initialization:{
   preferenceId: preferenceId_OK,

   },
   });
    }
    generateButton()
    }