import { Toast } from "react-bootstrap";
import '../assets/styles/alert-box.css';

/**
 * AlertBox component displays an error message with a toast notification.
 * It also includes a loading animation represented by the reverse-load div.
 *
 * @component
 * @param {object} props - The component props.
 * @param {string} props.errorMessage - The error message to be displayed.
 * @returns {JSX.Element} The rendered AlertBox component.
 * 
 * @author Anurag Gulati
 * @date 15/09/2024
 */
function AlertBox(props) {
    return (
        <div className="error-toast-div">
            <div className="error-toast">
                <Toast>
                    <Toast.Body>
                        <span className="error-message">
                            <i className="fi fi-br-exclamation"></i> {props.errorMessage}
                        </span>
                    </Toast.Body>
                </Toast>
                <div className="reverse-load" />
            </div>
        </div>
    );
}

export default AlertBox;
