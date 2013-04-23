function vaihdaLomake() {
    var selector = document.getElementById("viiteTyyppi");
    var selected = selector.options[selector.selectedIndex].value;
    console.log("valittu lomake: " + selected);

    var selectorForm = document.getElementById("viiteTyyppiLomake");
    selectorForm.submit();
}