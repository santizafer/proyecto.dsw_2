const path = window.location.pathname.split("/").pop();
/*Slider Home*/
function sliderMain() {
    if(path == "/" || path == "") {
        let slider = document.querySelector("#slider");
        let sliderInterval = 4000;
        let i = 0;
        setInterval(()=> {
            slider.children[i].classList.add("is-hidden");
            i++;
            if(i >= slider.children.length) {i = 0}
            slider.children[i].classList.remove("is-hidden");
        }, sliderInterval);
    }
};

/*Gallery Videos*/
function videoGaleria() {
    if(path == "videos") {
        const video_l = document.querySelector("#video-list");
        const video_c = document.querySelector("#video-content");
        const video_i = video_l.querySelectorAll("a img");

        for(let i=0; i<video_i.length; i++) {
            let id_video = video_i[i].closest("a").getAttribute("video_id");
            video_i[i].setAttribute("src", "https://img.youtube.com/vi/"+id_video+"/mq1.jpg");
        }

        video_l.addEventListener("click", (e)=> {
        e.preventDefault();
        if(e.target.tagName == "IMG") {
            let a_attr = e.target.parentNode.attributes;
            video_c.querySelector("iframe").setAttribute("src", "https://www.youtube.com/embed/"+a_attr.video_id.value+"?&autoplay=1&controls=0&rel=0");
            video_c.querySelector("#video-caption .text").textContent = a_attr.video_caption.value;
            }
        })
    }
}

/*Galería*/
function galeria() {
    if(path == "galeria") {
        const galeria = document.querySelector("#galeria");
        galeria.addEventListener("click", (e)=> {
            e.preventDefault();
            if(e.target.tagName == "IMG") {
                console.log(e);
                let modal = document.createElement("div");
                let modal_inner = document.createElement("div");
                let modal_close = document.createElement("div");
                let modal_descrip = document.createElement("div");
                modal.setAttribute("class", "modal-content");
                modal_inner.setAttribute("class", "modal-inner");
                modal_close.setAttribute("class", "modal-close");
                modal_descrip.setAttribute("class", "modal-descrip");
                modal.appendChild(modal_inner);
                modal_inner.appendChild(e.target.cloneNode(true));
                modal_inner.appendChild(modal_close);
                modal_inner.appendChild(modal_descrip);
                modal.classList.add("show");
                document.body.appendChild(modal);
                modal_descrip.textContent = e.target.alt;
                modal_close.innerHTML = "<i class='fa-sharp fa-solid fa-rectangle-xmark'></i>"
                modal_close.addEventListener("click", ()=> {
                    modal.remove();
                });
            }
        });
    }

}

/*Formulario de contáctenos*/
function contactenos() {
    if(path == "contactenos") {

        const NAME = /^[A-Z]+$/i;
        const EMAIL = /^\w+([.-_+]?\w+)@\w+([.-]?\w+)(\.\w{2,10})+$/;
        const SUJECT = /^([a-zA-Z0-9_-]){1,40}$/;
        const MESSAGE = /^([a-zA-Z0-9_-]){1,160}$/;

        let contactForm = document.getElementById("basic-form");

        if (contactForm) {
            contactForm.addEventListener("submit", (e) => {

                e.preventDefault();

                if (!NAME.test(contactForm.visitor_name.value)) {
                    alert('El nombre debe contener solo letras.');
                    return;
                }
                if (!EMAIL.test(contactForm.visitor_email.value)) {
                    alert('Ingrese una cuenta de correo válida.');
                    return;
                }
                if (!SUJECT.test(contactForm.email_title.value)) {
                    alert('Ingrese un asunto alfanumérico y que no supere los 40 caracteres.');
                    return;
                }
                if (!MESSAGE.test(contactForm.visitor_message.value)) {
                    alert('Ingrese un mensaje alfanumérico y que no supere los 160 caracteres.');
                    return;
                }

                document.basicForm.action = "contact_form.php";
                document.basicForm.method = "POST";
                document.basicForm.submit();
            });
        }

    }

}

/*Formulario de contactenos em los atractivos turísticos*/
function comments () {
    if(window.template == "atractivos") {

        var formComments = document.querySelector("form[name='comments']");
        var txtTitle = formComments.querySelector("input[name='titulo']");
        var txtName = formComments.querySelector("input[name='nombre']");
        var txtEmail = formComments.querySelector("input[name='email']");
        var message = formComments.querySelector("textarea");
        const EMAIL = /^\w+([.-_+]?\w+)*@\w+([.-]?\w+)*(\.\w{2,10})+$/;
        const SOLOCARACTERES_REGEX = /^[a-zA-Z\s]+$/;

        formComments.addEventListener("submit", (e) => {
            e.preventDefault();

            if(message.value == "") {
                alert("El mensaje no debe estár vacío.");
                return;
            }

            if(!SOLOCARACTERES_REGEX.test(txtTitle.value) || !SOLOCARACTERES_REGEX.test(txtName.value)) {
                alert("No dejar los cambos vacíos");
                return;
            }

            if (!EMAIL.test(txtEmail.value)) {
                alert('Ingrese una cuenta de correo válida.');
                return;
            }

            var commentItemsContent = document.querySelector(".comment-items-content");
            var newRow = document.createElement("div");
            var newDivTitle = document.createElement("div");
            var newDivName = document.createElement("div");
            var newDivEmail = document.createElement("div");
            var newDivMessage = document.createElement("div");
            var newDivContact = document.createElement("div");
            var newImgEmoji = document.createElement("img");

            var textTitle = document.createTextNode(txtTitle.value);
            var textName = document.createTextNode(txtName.value);
            var textEmail = document.createTextNode(txtEmail.value);
            var textMessage = document.createTextNode(message.value);
            var textEmoji = document.createTextNode(formComments.querySelector("input[name='caritas']:checked").value);
            newDivTitle.appendChild(textTitle);
            newDivName.appendChild(textName);
            newDivEmail.appendChild(textEmail);
            newDivMessage.appendChild(textMessage);
            newImgEmoji.src = `images/${textEmoji.textContent}.png`;
            newRow.setAttribute("class", "item");
            newDivTitle.setAttribute("class", "comment-title");
            newDivTitle.appendChild(newImgEmoji);
            newDivName.setAttribute("class", "comment-name");
            newDivEmail.setAttribute("class", "comment-email");
            newDivMessage.setAttribute("class", "comment-message");
            newDivContact.setAttribute("class", "comment-contact");
            newDivContact.appendChild(newDivName);
            newDivContact.appendChild(newDivEmail);
            newRow.appendChild(newDivTitle);
            newRow.appendChild(newDivMessage);
            newRow.appendChild(newDivContact);
            commentItemsContent.appendChild(newRow);
            limpiarInputs();
        });

    }

    function limpiarInputs() {
        txtTitle.value = "";
        txtName.value = "";
        txtEmail.value = "";
        message.value = "";
    }

}

/*Widget compartir URL en las redes sociales*/
function widgetCompartir () {
    if(document.querySelector("#compartir")) {
        const compartir_fb = document.querySelector("#compartir .fb");
        const compartir_tw = document.querySelector("#compartir .tw");
        const compartir_wa = document.querySelector("#compartir .wa");
        const compartir_in = document.querySelector("#compartir .in");
        const url = window.location.href;
        const wasaptel = "51998008615";
        const texto = encodeURIComponent("Te recomiendo esta web");
        compartir_fb.setAttribute("href", "https://www.facebook.com/sharer/sharer.php?u="+url);
        compartir_tw.setAttribute("href", `https://twitter.com/intent/tweet?text=${texto}&url=${url}`);
        compartir_wa.setAttribute("href", `https://wa.me/${wasaptel}?text=${texto}`);
        compartir_in.setAttribute("href", "https://www.linkedin.com/sharing/share-offsite/?url="+url);
    }
}

function init() {
  sliderMain();
  videoGaleria();
  galeria();
  contactenos();
  comments();
  widgetCompartir();
}

init();