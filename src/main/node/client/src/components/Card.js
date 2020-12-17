import PropTypes from 'prop-types'

function Card(props) {
  return props.onClick ? (
    <div className={"card " + props.className} onClick={props.onClick}>
      {props.children}
    </div>
  ) : (
    <button className={"card " + props.className} onClick={props.onClick}>
      {props.children}
    </button>
  )
}

Card.propTypes = {
  className: PropTypes.string,
  onClick: PropTypes.func
}

export default Card

