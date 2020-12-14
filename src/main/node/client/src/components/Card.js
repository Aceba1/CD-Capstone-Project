import PropTypes from 'prop-types'

function Card(props) {
  return (
    <div className={"card " + props.className}>
      {props.children}
    </div>
  )
}

Card.propTypes = {
  className: PropTypes.string
}

export default Card

