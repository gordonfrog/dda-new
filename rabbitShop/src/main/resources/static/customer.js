function sendCUST(){
              
            let result = document.querySelector('.result');
            let name = document.querySelector('#name');
            let custOccupation = document.querySelector('#custOccupation');
            let description = document.querySelector('#description');
            let type = document.querySelector('#type');
              
            // Creating a XHR object
            let xhr = new XMLHttpRequest();
            let url = "/sendMsgCustomer.rest";
       
            // open a connection
            xhr.open("POST", url, true);
 
            // Set the request header i.e. which type of content you are sending
            xhr.setRequestHeader("Content-Type", "application/json");
 
            // Create a state change callback
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
 
                    // Print received data from server
                    result.innerHTML = this.responseText;
 
                }
            };
 
            // Converting JSON data to string
            var data = JSON.stringify({ "custName": name.value, "custOccupation": custOccupation.value, "description": description.value, "type": type.value });
 
            // Sending data with the request
            xhr.send(data);
        }