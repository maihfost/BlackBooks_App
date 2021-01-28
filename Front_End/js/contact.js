function processForm(e) {
    if (e.preventDefault) e.preventDefault();

    $('#exampleModal').modal("show");
      return false;
  }

var form = document.getElementById('myform');

console.log(form)
if (form.attachEvent) {
    form.attachEvent("submit", processForm);
} else {
    form.addEventListener("submit", processForm);
}