function calculateGrades() {

    let attendance = parseFloat(document.getElementById('attendance').value) || 0;
    let lab1 = parseFloat(document.getElementById('lab1').value) || 0;
    let lab2 = parseFloat(document.getElementById('lab2').value) || 0;
    let lab3 = parseFloat(document.getElementById('lab3').value) || 0;

    if (attendance < 0 || attendance > 100 || lab1 < 0 || lab1 > 100 || lab2 < 0 || lab2 > 100 || lab3 < 0 || lab3 > 100) {
        alert("Please enter values between 0 and 100 for all fields.");
        return;
    }

    const labAverage = (lab1 + lab2 + lab3) / 3;

    const classStanding = (attendance * 0.40) + (labAverage * 0.60);

    const requiredForPassing = (75 - (classStanding * 0.3)) / 0.7;
    const requiredForExcellent = (100 - (classStanding * 0.3)) / 0.7;

    let passingMessage = requiredForPassing > 100 ? "Sorry, you can't get the passing grade by doing exam" : requiredForPassing.toFixed(2);
    let excellentMessage = requiredForExcellent > 100 ? "Sorry, you can't get the excellent grade by doing exam" : requiredForExcellent.toFixed(2);

    let prelimRemarks = "";
    if (classStanding >= 90) prelimRemarks = "Excellent standing so far";
    else if (classStanding >= 80) prelimRemarks = "Very Good standing";
    else if (classStanding >= 75) prelimRemarks = "Good standing";
    else if (classStanding >= 70) prelimRemarks = "Passing standing";
    else prelimRemarks = "Needs improvement";

    const resultsDiv = document.getElementById('results');
    resultsDiv.style.display = "block";
    resultsDiv.innerHTML = `
        <h3>✅ Computed Grades</h3>
        <p><strong>Attendance Score:</strong> ${attendance.toFixed(2)}</p>
        <p><strong>Lab Work 1 Grade:</strong> ${lab1.toFixed(2)}</p>
        <p><strong>Lab Work 2 Grade:</strong> ${lab2.toFixed(2)}</p>
        <p><strong>Lab Work 3 Grade:</strong> ${lab3.toFixed(2)}</p>
        <p><strong>Lab Work Average:</strong> ${labAverage.toFixed(2)}</p>
        <p><strong>Class Standing (30% of grade):</strong> ${classStanding.toFixed(2)}</p>
        <p><strong>Remarks:</strong> ${prelimRemarks}</p>
        <p><strong>Required Prelim Exam Score to Pass (75):</strong> ${passingMessage}</p>
        <p><strong>Required Prelim Exam Score for Excellent (100):</strong> ${excellentMessage}</p>
    `;
}