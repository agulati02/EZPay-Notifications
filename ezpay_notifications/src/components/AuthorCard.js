import "../assets/styles/AuthorCard.css"

const AuthorCard = (props) => {
    return (
        <div className="author-section">
            <h3>{props.author.name}</h3>
            <p>
                <i class="fi fi-br-briefcase" style={{'marginRight':'4px'}} /> <span>{props.author.designation}</span> <br />
                <i class="fi fi-bs-envelope" style={{'marginRight':'4px'}} /> <span>{props.author.email}</span>
            </p>
        </div>
    )
}

export default AuthorCard;