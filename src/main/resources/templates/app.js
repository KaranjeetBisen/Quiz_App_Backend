const API_URL = 'http://localhost:8080/question'; // Change if your backend is hosted elsewhere

// Add/Update a question
document.getElementById('questionForm').addEventListener('submit', async function (e) {
    e.preventDefault();

    const id = document.getElementById('questionId').value;
    const questionData = {
        id: document.getElementById('id').value,
        category: document.getElementById('category').value,
        questionTitle: document.getElementById('questionTitle').value,
        option1: document.getElementById('option1').value,
        option2: document.getElementById('option2').value,
        option3: document.getElementById('option3').value,
        option4: document.getElementById('option4').value,
        rightAnswer: document.getElementById('rightAnswer').value,
        difficultyLevel: document.getElementById('difficultyLevel').value
    };

    try {
        let response;
        if (id) {
            response = await fetch(`${API_URL}/upd/${id}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(questionData)
            });
        } else {
            response = await fetch(`${API_URL}/add`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(questionData)
            });
        }

        const result = await response.json();
        alert(result);
        getAllQuestions();
    } catch (error) {
        console.error('Error:', error);
    }
});

// Get all questions
async function getAllQuestions() {
    try {
        const response = await fetch(`${API_URL}/allQuestions`);
        const questions = await response.json();
        displayQuestions(questions);
    } catch (error) {
        console.error('Error:', error);
    }
}

// Get questions by category
async function getQuestionsByCategory() {
    const category = prompt('Enter the category:');
    try {
        const response = await fetch(`${API_URL}/category/${category}`);
        const questions = await response.json();
        displayQuestions(questions);
    } catch (error) {
        console.error('Error:', error);
    }
}

// Delete a question
async function deleteQuestion(id) {
    try {
        await fetch(`${API_URL}/del/${id}`, {
            method: 'DELETE',
        });
        getAllQuestions();
    } catch (error) {
        console.error('Error:', error);
    }
}

// Display questions
// Display questions with ID
function displayQuestions(questions) {
    const output = document.getElementById('output');
    output.innerHTML = ''; // Clear any existing content
    questions.forEach(question => {
        const div = document.createElement('div');
        div.innerHTML = `
            <p><strong>ID: ${question.id}</strong></p>
            <p><strong>${question.questionTitle}</strong> (${question.difficultyLevel})</p>
            <p>Category: ${question.category}</p>
            <p>Options: ${question.option1}, ${question.option2}, ${question.option3}, ${question.option4}</p>
            <p>Right Answer: ${question.rightAnswer}</p>
            <button onclick="deleteQuestion(${question.id})">Delete</button>
        `;
        output.appendChild(div);
    });
}

