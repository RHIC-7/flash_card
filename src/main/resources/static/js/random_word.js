let currentWordIndex = 0;
let alreadyDisplayed = [];
let displayed = false;

document.getElementById('nextButton').addEventListener('click', function () {
    displayRandomWord();
});

document.getElementById('answerButton').addEventListener('click', function () {
    if (!displayed) {
        let descriptionElement = document.getElementById('wordDescription');
        descriptionElement.textContent = words[currentWordIndex].description;
        descriptionElement.style.visibility = 'visible'; // 説明を表示
        descriptionElement.style.color = 'inherit'; // 色を元に戻す
        displayed = true;
    } else {
        let descriptionElement = document.getElementById('wordDescription');
        descriptionElement.style.visibility = 'hidden'; // 説明を隠す
        descriptionElement.style.color = 'transparent'; // 色を透明に
        displayed = false;
    }
});

document.addEventListener('DOMContentLoaded', function () {
    displayRandomWord();
});

function displayRandomWord() {
    if (alreadyDisplayed.length === words.length) {
        alert("Finish");
        alreadyDisplayed = []; // 全ての単語が表示されたらリセット
        return; // リセット後の挙動を考慮
    }

    do {
        currentWordIndex = Math.floor(Math.random() * words.length);
    } while (alreadyDisplayed.includes(currentWordIndex));

    document.getElementById('wordDisplay').textContent = words[currentWordIndex].word;
    let descriptionElement = document.getElementById('wordDescription');
    descriptionElement.style.visibility = 'hidden'; // 説明を非表示に
    descriptionElement.style.color = 'transparent'; // 色を透明に
    descriptionElement.textContent = words[currentWordIndex].description; // テキスト内容を事前に設定
    displayed = false;

    alreadyDisplayed.push(currentWordIndex);
}
