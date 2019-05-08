function showCountForm() {
    let element = document.getElementById("count");
    element.hidden = false;
    element = document.getElementById("sub");
    element.hidden = true;
}

function showSubForm() {
    let element = document.getElementById("count");
    element.hidden = true;
    element = document.getElementById("sub");
    element.hidden = false;
}