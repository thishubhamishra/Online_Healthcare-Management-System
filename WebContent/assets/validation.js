// Basic example of event listeners for Review-2's "Event Handling" requirement

document.addEventListener("DOMContentLoaded", function () {
    const registerForm = document.getElementById("registerForm");
    if (registerForm) {
        registerForm.addEventListener("submit", function (e) {
            const password = registerForm.password.value;
            const confirm = registerForm.confirmPassword.value;
            if (password.length < 6) {
                alert("Password must be at least 6 characters.");
                e.preventDefault();
            } else if (password !== confirm) {
                alert("Passwords do not match.");
                e.preventDefault();
            }
        });
    }

    const appointmentForm = document.getElementById("appointmentForm");
    if (appointmentForm) {
        appointmentForm.addEventListener("submit", function (e) {
            const dateInput = appointmentForm.date.value;
            if (!dateInput) return;

            const selected = new Date(dateInput);
            const today = new Date();
            today.setHours(0,0,0,0);

            if (selected < today) {
                alert("Appointment date cannot be in the past.");
                e.preventDefault();
            }
        });
    }
});

