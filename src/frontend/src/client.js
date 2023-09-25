import fetch from 'unfetch';

const checkStatus = response => {
    if (response.ok) {
        return response;
    }
    // convert non-2xx HTTP responses into errors, complete with 400 and 500 statuses:
    let error = new Error(response.statusText);
    if (response.status >= 400 && response.status < 500) {
        error.message = 'Client error: ' + error.message;
    } else if (response.status >= 500) {
        error.message = 'Client error: ' + error.message;
    }
    error.response = response;
    return Promise.reject(error);
}
export const getAllData = () =>
    fetch("api/3a/output_data")
        .then(checkStatus);
