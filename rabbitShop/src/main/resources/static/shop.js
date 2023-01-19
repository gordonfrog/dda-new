function sendSHOP(){
              
            let result2 = document.querySelector('.result');
            let name2 = document.querySelector('#name');
            let city2 = document.querySelector('#city');
            let sales2 = document.querySelector('#sales');
            let type2 = document.querySelector('#type');
              
            // Creating a XHR object
            let xhr2 = new XMLHttpRequest();
            let url2 = "/sendMsgShop.rest";
       
            // open a connection
            xhr2.open("POST", url2, true);
 
            // Set the request header i.e. which type of content you are sending
            xhr2.setRequestHeader("Content-Type", "application/json");
 
            // Create a state change callback
            xhr2.onreadystatechange = function () {
                if (xhr2.readyState === 4 && xhr2.status === 200) {
 
                    // Print received data from server
                    result2.innerHTML = this.responseText;
 
                }
            };
 
            // Converting JSON data to string
            var data2 = JSON.stringify({ "shopName": name2.value, "city": city2.value, "sales": sales2.value, "type": type2.value });
 
            // Sending data with the request
            xhr2.send(data2);
        }