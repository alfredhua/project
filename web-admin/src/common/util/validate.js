
import moment  from 'moment';

export function validate_phone(phone) {
  const myreg=/^[1][3,4,5,6,7,8,9][0-9]{9}$/;
  if (myreg.test(phone)) {
     return true;
  } else {
     return false;
  }
}

// export function validate_email(email) {
//   return /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/.test(email)
// }

export function time_formate(time){
  return time?moment(time).format('YYYY-MM-DD HH:mm:ss'):null;
}

export function date_formate(date){
  return date?moment(date).format('YYYY-MM-DD'):null;
}



