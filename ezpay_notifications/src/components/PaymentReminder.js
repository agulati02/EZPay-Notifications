import Header from "./Header";
import ReminderTitle from "./ReminderTitle";
import "../assets/styles/PaymentReminder.css";
import "../assets/styles/common-styles.css";
import { useEffect, useRef, useState } from "react";
import BubblesBackground from "./BubblesBackground";
/*Author Name: Anurag Gulati and Doneela Das and Date: 14/09/2024 */
/*External Module: Header, Reminder Title and Bubbles Background*/
/**
 * The PaymentReminder component manages payment reminders, including fetching, adding, and deleting reminders.
 * It renders a list of reminders and provides an interface for adding new reminders and confirming deletion of existing ones.
 * 
 * @component
 * @returns {JSX.Element} The rendered payment reminder component
 */
function PaymentReminder() {

    // Local variable to hold reminders
    let reminders = [];

    // State for storing the list of reminders
    const [reminderList, setReminderList] = useState([]);
    const [showAddForm, setShowAddForm] = useState(false);
    const [showPopup, setShowPopup] = useState(false);
    const [reminderIdToBeDeleted, setReminderIdToBeDeleted] = useState("");

    const today = new Date().toISOString().split("T")[0]; // Today's date in YYYY-MM-DD format

    /**
     * Helper function to format a date string from YYYY-MM-DD to DD-MM-YYYY.
     * 
     * @param {string} dateString - Date string in YYYY-MM-DD format
     * @returns {string} - Formatted date string in DD-MM-YYYY format
     */
    const formatDate = (dateString) => {
        const [year, month, day] = dateString.split("-");
        return `${day}-${month}-${year}`;
    };

    /**
     * Fetches payment reminders for the current user from the server.
     * 
     * @async
     */
    async function fetchPaymentReminders() {
        let response = await fetch(`http://localhost:9090/api/payment-reminders/fetch/${localStorage.getItem("userId")}`, {
            "method": "GET"
        });
        reminders = await response.json();
        console.log(reminders);
        setReminderList(reminders);
    }

    /**
     * Deletes a specific payment reminder.
     * 
     * @param {string} reminderId - ID of the reminder to be deleted
     * @async
     */
    async function deletePaymentReminder(reminderId) {
        let response = await fetch(`http://localhost:9090/api/payment-reminders/delete/${reminderId}`, {
            "method": "DELETE"
        });
        if (response.ok) {
            await fetchPaymentReminders(); // Refresh the reminder list
        } else {
            throw new Error("Reminder Deletion Failed!");
        }
    }

    /**
     * Adds a new payment reminder.
     * 
     * @param {object} reminder - The reminder object containing userId, amount, dueDate, and status
     * @async
     */
    async function addPaymentReminder({ userId, amount, dueDate, status }) {
        let response = await fetch(`http://localhost:9090/api/payment-reminders/add?userId=${userId}&amount=${amount}&dueDate=${dueDate}&status=${status}`, {
            "method": "POST"
        });
        if (response.ok) {
            await fetchPaymentReminders(); // Refresh the reminder list
        } else {
            throw new Error("Reminder Addition Failed!");
        }
    }

    // Fetch reminders when the component loads
    useEffect(() => {
        fetchPaymentReminders();
    }, []);

    /**
     * Toggles the form for adding a new reminder.
     */
    const handleAddReminderForm = () => {
        setShowAddForm(!showAddForm);
    };

    /**
     * Opens the delete confirmation popup for a specific reminder.
     * 
     * @param {string} reminderId - The ID of the reminder to be deleted
     */
    const handleDeleteReminder = (reminderId) => {
        setShowPopup(true);
        setReminderIdToBeDeleted(reminderId);
    };

    /**
     * Confirms deletion of the reminder.
     */
    const handleConfirmDelete = () => {
        deletePaymentReminder(reminderIdToBeDeleted);
        setShowPopup(false);
        setReminderIdToBeDeleted("");
    };

    /**
     * Cancels the reminder deletion.
     */
    const handleCancelDelete = () => {
        setShowPopup(false);
    };

    // Refs for form inputs
    const amountRef = useRef();
    const dueDateRef = useRef();
    const statusRef = useRef();

    /**
     * Handles adding a new reminder by validating inputs and calling the add function.
     */
    const handleAddReminder = () => {
        if (Number(amountRef.current.value) < 0) {
            alert("Payment amount cannot be negative");
            return;
        }
        const dueDateString = formatDate(dueDateRef.current.value);
        let reminder = {
            userId: localStorage.getItem("userId"),
            amount: Number(amountRef.current.value),
            dueDate: dueDateString,
            status: statusRef.current.value
        };
        addPaymentReminder({ ...reminder });
        setShowAddForm(false);
    };

    return (
        <>
            <BubblesBackground />
            <Header />
            
            <div className="main-container">
                <ReminderTitle />
                {/* Render the list of reminders */}
                {reminderList.map((rem, index) => (
                    <div className="reminder" key={index}>
                        <div className="rem-point">
                            <button className="delete-reminder-button" onClick={() => handleDeleteReminder(rem.reminderId)}>
                                <i className="fa fa-trash-o"></i>
                            </button>
                            <label htmlFor={`reminder_${index}`} className="rem-text">
                                Payment of <b>&#8377; {rem.amount}</b> is due on <b>{new Date(rem.dueDate).toLocaleDateString('en-GB')}</b>.
                            </label>
                        </div>
                    </div>
                ))}
                {/* Delete confirmation popup */}
                {showPopup && (
                    <div className={`popup-overlay ${showPopup ? 'show' : ''}`}>
                        <div className={`popup ${showPopup ? 'show' : ''}`}>
                            <h2>Delete Reminder</h2>
                            <p>Do you want to delete this reminder?</p>
                            <button onClick={handleConfirmDelete}>Yes, Delete</button>
                            <button onClick={handleCancelDelete}>Cancel</button>
                        </div>
                    </div>
                )}
                {/* Add Reminder button and form */}
                <div className="add-pr-div">
                    <div className="add-pr-button-container">
                        <button className="add-pr-button" onClick={handleAddReminderForm}>
                            <span>+</span>
                        </button>
                    </div>
                    <div className={"add-pr-form-overlay " + (showAddForm ? "display-form-overlay" : null)}>
                        <div className={"add-pr-form-container " + (showAddForm ? "display-form" : null)}>
                            <div className="add-pr-form-title">
                                <h3>Add Payment Reminder</h3>
                            </div>
                            <div className="add-pr-form">
                                <div className="form-field">
                                    <p>Payment amount: <span style={{ color: 'red' }}>*</span></p>
                                    <input type="number" placeholder="Enter amount" ref={amountRef} required />
                                </div>
                                <div className="form-field">
                                    <p>Due date: <span style={{ color: 'red' }}>*</span></p>
                                    <input type="date" min={today} placeholder="(DD-MM-YYYY)" ref={dueDateRef} required />
                                </div>
                                <div className="form-field">
                                    <p>Status: <span style={{ color: 'red' }}>*</span></p>
                                    <input type="text" placeholder="Enter status" ref={statusRef} required />
                                </div>
                                <div className="form-field">
                                    <div className="form-button-container">
                                        <button className="add-rem-button" onClick={handleAddReminder}>
                                            Add Reminder
                                        </button>
                                        <button className="cancel-rem-button" onClick={() => setShowAddForm(false)} style={{ width: '30%' }}>
                                            Cancel
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
}

export default PaymentReminder;
