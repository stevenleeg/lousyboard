let inputEl;

function onLoad() {
  inputEl = document.getElementById('post-input');

  const posts = document.getElementsByClassName('post-id');
  Array.prototype.forEach.call(posts, (postEl) => {
    postEl.addEventListener('click', addQuote);
  });
}

function addQuote(e) {
  const id = e.target.getAttribute('data-post-id');
  inputEl.value = `@${id} ${inputEl.value}`;
}

document.addEventListener('DOMContentLoaded', onLoad);
