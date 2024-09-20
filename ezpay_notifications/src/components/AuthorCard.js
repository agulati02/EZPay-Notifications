import "../assets/styles/AuthorCard.css";

/**
 * A React component that renders a card with author details.
 * 
 * @component
 * @param {Object} props - Component properties
 * @param {Object} props.author - The author object containing details to be displayed
 * @param {string} props.author.name - The name of the author
 * @param {string} props.author.designation - The designation/job title of the author
 * @param {string} props.author.email - The email address of the author
 * @returns {JSX.Element} The rendered author card component
 * 
 * @author Anurag Gulati
 * @date 14/09/2024
 */
const AuthorCard = (props) => {
    return (
        <div className="author-section">
            <h3>{props.author.name}</h3>
            <p>
                <i className="fi fi-br-briefcase" style={{ marginRight: '4px' }} /> 
                <span>{props.author.designation}</span> <br />
                <i className="fi fi-bs-envelope" style={{ marginRight: '4px' }} /> 
                <span>{props.author.email}</span>
            </p>
        </div>
    );
};

export default AuthorCard;
