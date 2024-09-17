import Header from "./Header";
import ReminderTitle from "./ReminderTitle";
import "../assets/styles/PaymentReminder.css";
import "../assets/styles/common-styles.css";
import { useEffect, useRef, useState } from "react";
import BubblesBackground from "./BubblesBackground";

function PaymentReminder() {

    let reminders = [];

    const [reminderList, setReminderList] = useState([]);
    const [showAddForm, setShowAddForm] = useState(false);
    const [showPopup, setShowPopup] = useState(false);
    const [reminderIdToBeDeleted, setReminderIdToBeDeleted] = useState("");

    const today = new Date().toISOString().split("T")[0];
    const formatDate = (dateString) => {
        const [year, month, day] = dateString.split("-");
        return `${day}-${month}-${year}`;
    };

    async function fetchPaymentReminders() {
        let response = await fetch(`http://localhost:9090/api/payment-reminders/fetch/${localStorage.getItem("userId")}`, {
            "method": "GET"
        });
        reminders = await response.json();
        console.log(reminders);
        setReminderList(reminders);
    }

    async function deletePaymentReminder(reminderId) {
        let response = await fetch(`http://localhost:9090/api/payment-reminders/delete/${reminderId}`, {
            "method": "DELETE"
        });
        if (response.ok) {
            await fetchPaymentReminders();
        } else {
            throw new Error("Reminder Deletion Failed!");
        }
    }

    async function addPaymentReminder({ userId, amount, dueDate, status }) {
        let response = await fetch(`http://localhost:9090/api/payment-reminders/add?userId=${userId}&amount=${amount}&dueDate=${dueDate}&status=${status}`, {
            "method": "POST"
        });
        if (response.ok) {
            await fetchPaymentReminders();
        } else {
            throw new Error("Reminder Addition Failed!");
        }
    }

    useEffect(() => {
        fetchPaymentReminders();
    }, [])

    const handleAddReminderForm = () => {
        setShowAddForm(!showAddForm);
    }

    const handleDeleteReminder = (reminderId) => {
        setShowPopup(true);
        setReminderIdToBeDeleted(reminderId);
    }

    const handleConfirmDelete = () => {
        deletePaymentReminder(reminderIdToBeDeleted);
        setShowPopup(false);
        setReminderIdToBeDeleted("");
    }

    const handleCancelDelete = () => {
        setShowPopup(false);
    }

    const amountRef = useRef();
    const dueDateRef = useRef();
    const statusRef = useRef();

    const handleAddReminder = () => {
        if(Number(amountRef.current.value) < 0) {
            alert("Payment amount can not be negative");
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
    }

    return (
        <>
        <BubblesBackground />
        <Header />
        
        <div className="main-container">
            <ReminderTitle />
            {
                reminderList.map((rem, index) => {
                    return (
                        <div className="reminder">
                            <div className="rem-point">
                                <button className="delete-reminder-button" onClick={(e) => { handleDeleteReminder(rem.reminderId) }}>
                                    <i class="fa fa-trash-o"></i>
                                </button>
                                <label for={`reminder_${index}`} className="rem-text">
                                    Payment of <b>&#8377; {rem.amount}</b> is due on <b>{rem.dueDate.toLocaleString('en-GB', {
                                        day: "2-digit",
                                        month: "2-digit",
                                        year: "numeric"
                                    })}</b>.
                                </label>
                            </div>
                        </div>
                    )
                })
            }
            {
                showPopup && (
                    <div className={`popup-overlay ${showPopup ? 'show' : ''}`}>
                        <div className={`popup ${showPopup ? 'show' : ''}`}>
                            <h2>Delete Reminder</h2>
                            <p>Do you want to delete this reminder?</p>
                            <button onClick={(e) => handleConfirmDelete()}>Yes, Delete</button>
                            <button onClick={(e) => handleCancelDelete()}>Cancel</button>
                        </div>
                    </div>
                )
            }
            <div className="add-pr-div">
                <div className="add-pr-button-container">
                    <button className="add-pr-button" onClick={handleAddReminderForm}>
                        <span>+</span>
                    </button>
                </div>
                <div className={"add-pr-form-overlay " + ((showAddForm) ? "display-form-overlay" : null)}>
                    <div className={"add-pr-form-container " + ((showAddForm) ? "display-form" : null)}>
                        <div className="add-pr-form-title">
                            <h3>Add Payment Reminder</h3>
                        </div>
                        <div className="add-pr-form">
                            <div className="form-field">
                                <p>
                                    Payment amount: <span style={{ 'color': 'red' }}>*</span>
                                </p>
                                <input type="number" placeholder="Enter amount" ref={amountRef} required />
                            </div>
                            <div className="form-field">
                                <p>
                                    Due date: <span style={{ 'color': 'red' }}>*</span>
                                </p>
                                <input type="date" min={today} placeholder="(DD-MM-YYYY)" ref={dueDateRef} required />
                            </div>
                            <div className="form-field">
                                <p>
                                    Status: <span style={{ 'color': 'red' }}>*</span>
                                </p>
                                <input type="text" placeholder="Enter status" ref={statusRef} required />
                            </div>
                            <div className="form-field">
                                <div className="form-button-container">
                                    <button className="add-rem-button" onClick={(e) => { handleAddReminder() }}>
                                        Add Reminder
                                    </button>
                                    <button className="cancel-rem-button" onClick={(e) => { setShowAddForm(false) }} style={{'width':'30%'}}>
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