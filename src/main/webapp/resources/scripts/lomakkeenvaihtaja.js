function vaihdaLomake() {
    var selector = document.getElementById("viiteTyyppi");
    var selected = selector.options[selector.selectedIndex].value;
    console.log("valittu lomake: " + selected);

    var selectorForm = document.getElementById("viiteTyyppiLomake");
    selectorForm.submit();
}
function togglaaSetti(id) {
    if(document.getElementById(id).style.display === 'block')
        document.getElementById(id).style.display = 'none';
    else
        document.getElementById(id).style.display = 'block';
}