/* =====================================================
   API
===================================================== */

const API = "/api";


/* =====================================================
   PAGES
===================================================== */

const pages = {

    dashboard: [
        "Dashboard",
        "Hotel operations overview"
    ],

    rooms: [
        "Room Management",
        "Manage rooms, availability and facilities"
    ],

    guests: [
        "Guest Management",
        "Manage guest records"
    ],

    reservations: [
        "Reservations",
        "Booking and reservation management"
    ],

    checkins: [
        "Check-In",
        "Guest arrival and room assignment"
    ],

    checkouts: [
        "Check-Out",
        "Billing and departure"
    ],

    food: [
        "Food Orders",
        "Restaurant and room-service orders"
    ],

    services: [
        "Hotel Services",
        "Laundry, spa, parking, room service and more"
    ],

    housekeeping: [
        "Housekeeping",
        "Cleaning and room preparation"
    ],

    payments: [
        "Payments",
        "Payment transactions and billing"
    ],

    employees: [
        "Employees",
        "Staff and department management"
    ],

    feedback: [
        "Feedback",
        "Customer ratings and suggestions"
    ],

    maintenance: [
        "Maintenance",
        "Room and equipment maintenance"
    ],

    notifications: [
        "Notifications",
        "Hotel alerts and updates"
    ]

};


/* =====================================================
   API ENDPOINTS
===================================================== */

const endpoint = {

    rooms: "rooms",

    guests: "guests",

    reservations: "reservations",

    checkins: "check-ins",

    checkouts: "check-outs",

    food: "food-orders",

    services: "services",

    housekeeping: "housekeeping",

    payments: "payments",

    employees: "employees",

    feedback: "feedback",

    maintenance: "maintenance",

    notifications: "notifications"

};


/* =====================================================
   GLOBAL VARIABLES
===================================================== */

window.currentData = [];

window.currentPage = "dashboard";


/* =====================================================
   NAVIGATION
===================================================== */

document.querySelectorAll(".nav").forEach(a => {

    a.onclick = () => {

        navigate(
            a.dataset.page
        );

    };

});


function navigate(page) {

    document.querySelectorAll(".nav").forEach(item => {

        item.classList.toggle(
            "active",
            item.dataset.page === page
        );

    });


    document.getElementById(
        "pageTitle"
    ).textContent =
        pages[page][0];


    document.getElementById(
        "pageSub"
    ).textContent =
        pages[page][1];


    if (page === "dashboard") {

        loadDashboard();

    } else {

        loadCrud(page);

    }

}


/* =====================================================
   MONEY
===================================================== */

function money(value) {

    return "₹" +
        Number(value || 0)
            .toLocaleString("en-IN");

}


/* =====================================================
   DASHBOARD
===================================================== */

function loadDashboard() {

    fetch(
        API + "/dashboard"
    )

    .then(response => {

        if (!response.ok) {

            throw new Error(
                "Dashboard API failed"
            );

        }

        return response.json();

    })

    .then(data => {

        document.getElementById(
            "content"
        ).innerHTML = `

            <div class="cards">

                ${metric(
                    "🛏️",
                    "Total Rooms",
                    data.rooms
                )}

                ${metric(
                    "✅",
                    "Available",
                    data.availableRooms
                )}

                ${metric(
                    "🔴",
                    "Occupied",
                    data.occupiedRooms
                )}

                ${metric(
                    "📅",
                    "Reservations",
                    data.reservations
                )}

                ${metric(
                    "👤",
                    "Guests",
                    data.guests
                )}

                ${metric(
                    "👨‍💼",
                    "Employees",
                    data.employees
                )}

                ${metric(
                    "⭐",
                    "Feedback",
                    data.feedback
                )}

                ${metric(
                    "💰",
                    "Revenue",
                    money(data.revenue)
                )}

            </div>


            <div class="grid">


                <!-- QUICK ACTIONS -->

                <div class="section">

                    <h3>
                        Quick Actions
                    </h3>


                    <div class="quick">

                        <button
                            onclick="navigate('rooms')">

                            🛏️
                            <br>

                            <b>
                                Manage Rooms
                            </b>

                            <br>

                            <span class="muted">
                                Availability & booking
                            </span>

                        </button>


                        <button
                            onclick="navigate('reservations')">

                            📅
                            <br>

                            <b>
                                New Reservation
                            </b>

                            <br>

                            <span class="muted">
                                Start booking flow
                            </span>

                        </button>


                        <button
                            onclick="navigate('checkins')">

                            🛎️
                            <br>

                            <b>
                                Guest Check-In
                            </b>

                            <br>

                            <span class="muted">
                                Assign room
                            </span>

                        </button>


                        <button
                            onclick="navigate('checkouts')">

                            🚪
                            <br>

                            <b>
                                Guest Check-Out
                            </b>

                            <br>

                            <span class="muted">
                                Bill & invoice
                            </span>

                        </button>


                        <button
                            onclick="navigate('food')">

                            🍽️
                            <br>

                            <b>
                                Food Order
                            </b>

                            <br>

                            <span class="muted">
                                Restaurant service
                            </span>

                        </button>


                        <button
                            onclick="navigate('services')">

                            🧹
                            <br>

                            <b>
                                Hotel Services
                            </b>

                            <br>

                            <span class="muted">
                                Create service request
                            </span>

                        </button>


                        <button
                            onclick="navigate('feedback')">

                            ⭐
                            <br>

                            <b>
                                Feedback
                            </b>

                            <br>

                            <span class="muted">
                                Customer experience
                            </span>

                        </button>


                        <button
                            onclick="openChat()">

                            💬
                            <br>

                            <b>
                                Chatbot
                            </b>

                            <br>

                            <span class="muted">
                                24/7 support
                            </span>

                        </button>

                    </div>

                </div>


                <!-- ROOM STATUS -->

                <div class="section">

                    <h3>
                        Room Status
                    </h3>


                    ${
                        Object.entries(
                            data.roomStatus || {}
                        )
                        .map(
                            ([key, value]) => `

                                <p>

                                    <b>
                                        ${key || "UNKNOWN"}
                                    </b>

                                    <span
                                        style="float:right">
                                        ${value}
                                    </span>

                                </p>

                            `
                        )
                        .join("")
                    }

                </div>

            </div>

        `;

    })

    .catch(showError);

}


/* =====================================================
   METRIC
===================================================== */

function metric(
    icon,
    label,
    value
) {

    return `

        <div class="card">

            <div>

                ${icon}

                <span class="muted">
                    ${label}
                </span>

            </div>


            <div class="metric">
                ${value}
            </div>

        </div>

    `;

}


/* =====================================================
   CRUD
===================================================== */

async function loadCrud(page) {

    const ep =
        endpoint[page];


    window.currentPage =
        page;


    document.getElementById(
        "content"
    ).innerHTML = `

        <div class="section">

            <div class="toolbar">

                <input
                    id="search"
                    placeholder="Search..."
                >


                <button
                    class="primary"
                    onclick="openForm('${page}')">

                    + Add
                    ${pages[page][0]}

                </button>

            </div>


            <div
                id="table"
                class="table-wrap">

                Loading...

            </div>

        </div>

    `;


    try {

        const response =
            await fetch(
                API + "/" + ep
            );


        if (!response.ok) {

            throw new Error(
                "API request failed"
            );

        }


        const data =
            await response.json();


        window.currentData =
            data;


        renderTable(
            data,
            page
        );

    }

    catch (error) {

        console.error(
            error
        );

        showError(
            error
        );

    }

}


/* =====================================================
   RENDER TABLE
===================================================== */

function renderTable(
    data,
    page
) {

    const table =
        document.getElementById(
            "table"
        );


    if (
        !Array.isArray(data) ||
        data.length === 0
    ) {

        table.innerHTML =
            "<p class='muted'>No records found.</p>";

        return;

    }


    const keys =
        Object.keys(
            data[0]
        )
        .filter(
            key => key !== "id"
        )
        .slice(
            0,
            7
        );


    table.innerHTML = `

        <table class="table">

            <thead>

                <tr>

                    ${
                        keys
                        .map(
                            key =>
                                `<th>
                                    ${pretty(key)}
                                </th>`
                        )
                        .join("")
                    }


                    <th>
                        Action
                    </th>

                </tr>

            </thead>


            <tbody>

                ${
                    data
                    .map(
                        item => `

                            <tr>

                                ${
                                    keys
                                    .map(
                                        key =>
                                            `<td>
                                                ${formatValue(
                                                    item[key]
                                                )}
                                            </td>`
                                    )
                                    .join("")
                                }


                                <td class="actions">

                                    <button
                                        onclick='editItem(${JSON.stringify(item)})'>
                                        Edit
                                    </button>


                                    <button
                                        class="danger"
                                        onclick="deleteItem(${item.id})">
                                        Delete
                                    </button>

                                </td>

                            </tr>

                        `
                    )
                    .join("")
                }

            </tbody>

        </table>

    `;

}


/* =====================================================
   PRETTY FIELD NAME
===================================================== */

function pretty(value) {

    return value
        .replace(
            /([A-Z])/g,
            " $1"
        )
        .replace(
            /^./,
            character =>
                character.toUpperCase()
        );

}


/* =====================================================
   FORMAT TABLE VALUE
===================================================== */

function formatValue(value) {

    if (
        value === null ||
        value === undefined
    ) {

        return "-";

    }


    if (
        typeof value === "object"
    ) {

        return JSON.stringify(
            value
        );

    }


    return String(value);

}


/* =====================================================
   FORM FIELD DEFINITIONS
===================================================== */

const formFields = {

    rooms: [

        ["roomNumber", "Room Number"],

        ["roomType", "Room Type"],

        ["price", "Price"],

        ["capacity", "Capacity"],

        ["status", "Status"],

        ["floor", "Floor"],

        ["facilities", "Facilities"]

    ],


    guests: [

        ["fullName", "Full Name"],

        ["email", "Email"],

        ["phone", "Phone"],

        ["idProof", "ID Proof"],

        ["address", "Address"],

        ["dateOfBirth", "Date of Birth"]

    ],


    reservations: [

        ["bookingCode", "Booking Code"],

        ["guestId", "Guest ID"],

        ["roomId", "Room ID"],

        ["checkIn", "Check-In"],

        ["checkOut", "Check-Out"],

        ["guests", "Guests"],

        ["totalAmount", "Total Amount"],

        ["status", "Status"]

    ],


    checkins: [

        ["reservationId", "Reservation ID"],

        ["guestId", "Guest ID"],

        ["roomId", "Room ID"],

        ["checkInTime", "Check-In Time"],

        ["status", "Status"],

        ["notes", "Notes"]

    ],


    checkouts: [

        ["reservationId", "Reservation ID"],

        ["guestId", "Guest ID"],

        ["roomId", "Room ID"],

        ["checkOutTime", "Check-Out Time"],

        ["finalAmount", "Final Amount"],

        ["status", "Status"]

    ],


    food: [

        ["guestId", "Guest ID"],

        ["roomId", "Room ID"],

        ["itemName", "Item Name"],

        ["quantity", "Quantity"],

        ["amount", "Amount"],

        ["status", "Status"]

    ],


    services: [

        ["guestId", "Guest ID"],

        ["roomId", "Room ID"],

        ["serviceType", "Service Type"],

        ["description", "Description"],

        ["status", "Status"]

    ],


    housekeeping: [

        ["roomId", "Room ID"],

        ["assignedTo", "Assigned To"],

        ["taskType", "Task Type"],

        ["status", "Status"]

    ],


    payments: [

        ["reservationId", "Reservation ID"],

        ["guestId", "Guest ID"],

        ["amount", "Amount"],

        ["paymentMethod", "Payment Method"],

        [
            "transactionReference",
            "Transaction Reference"
        ],

        ["status", "Status"]

    ],


    employees: [

        ["employeeCode", "Employee Code"],

        ["fullName", "Full Name"],

        ["department", "Department"],

        ["role", "Role"],

        ["phone", "Phone"],

        ["email", "Email"],

        ["status", "Status"]

    ],


    feedback: [

        ["guestId", "Guest ID"],

        ["reservationId", "Reservation ID"],

        ["rating", "Overall Rating"],

        ["roomRating", "Room Rating"],

        ["foodRating", "Food Rating"],

        ["staffRating", "Staff Rating"],

        [
            "cleanlinessRating",
            "Cleanliness Rating"
        ],

        ["comments", "Comments"]

    ],


    maintenance: [

        ["roomId", "Room ID"],

        ["issue", "Issue"],

        ["priority", "Priority"],

        ["status", "Status"],

        ["assignedTo", "Assigned To"]

    ],


    notifications: [

        ["title", "Title"],

        ["message", "Message"],

        ["type", "Type"],

        ["isRead", "Read"]

    ]

};


/* =====================================================
   TEXTAREA FIELDS
===================================================== */

const textareaFields = [

    "comments",

    "description",

    "message",

    "facilities",

    "notes",

    "address"

];


/* =====================================================
   INPUT TYPE
===================================================== */

function getInputType(key) {

    /* ADDED/FIXED: Date of birth uses date */
    if (
        key === "dateOfBirth"
    ) {

        return "date";

    }


    /* ADDED/FIXED: LocalDateTime fields */
    if (

        key === "checkIn" ||

        key === "checkOut" ||

        key === "checkInTime" ||

        key === "checkOutTime"

    ) {

        return "datetime-local";

    }


    /* Number fields */

    if (

        key === "price" ||

        key === "capacity" ||

        key === "guestId" ||

        key === "roomId" ||

        key === "reservationId" ||

        key === "quantity" ||

        key === "amount" ||

        key === "rating" ||

        key === "roomRating" ||

        key === "foodRating" ||

        key === "staffRating" ||

        key === "cleanlinessRating" ||

        key === "guests"

    ) {

        return "number";

    }


    return "text";

}


/* =====================================================
   SAFE HTML VALUE
===================================================== */

function safeValue(value) {

    if (
        value === null ||
        value === undefined
    ) {

        return "";

    }


    return String(value)

        .replace(
            /&/g,
            "&amp;"
        )

        .replace(
            /</g,
            "&lt;"
        )

        .replace(
            />/g,
            "&gt;"
        )

        .replace(
            /"/g,
            "&quot;"
        )

        .replace(
            /'/g,
            "&#039;"
        );

}


/* =====================================================
   DATETIME FORMAT
===================================================== */

function formatDateTimeLocal(value) {

    if (!value) {

        return "";

    }


    const stringValue =
        String(value);


    if (
        stringValue.includes("T")
    ) {

        return stringValue.substring(
            0,
            16
        );

    }


    return stringValue;

}


/* =====================================================
   OPEN FORM
===================================================== */

function openForm(
    page,
    item = {}
) {

    console.log(
        "Opening form:",
        page,
        item
    );


    /*
       ADDED/FIXED:
       Get modal elements before doing anything.
    */

    const modal =
        document.getElementById(
            "modal"
        );


    const modalBody =
        document.getElementById(
            "modalBody"
        );


    if (
        !modal ||
        !modalBody
    ) {

        console.error(
            "Modal elements not found."
        );

        alert(
            "Modal is missing in index.html."
        );

        return;

    }


    const fields =
        formFields[page] || [];


    if (
        fields.length === 0
    ) {

        alert(
            "No form fields configured for " +
            page
        );

        return;

    }


    window.currentPage =
        page;


    /* =================================================
       CREATE FORM FIELDS
    ================================================= */

    const generatedFields =
        fields
        .map(
            ([key, label]) => {

                let value;


                if (

                    key === "checkIn" ||

                    key === "checkOut" ||

                    key === "checkInTime" ||

                    key === "checkOutTime"

                ) {

                    value =
                        formatDateTimeLocal(
                            item[key]
                        );

                }

                else {

                    value =
                        safeValue(
                            item[key]
                        );

                }


                /* TEXTAREA */

                if (
                    textareaFields.includes(
                        key
                    )
                ) {

                    return `

                        <div class="full">

                            <label>
                                ${label}
                            </label>

                            <textarea
                                name="${key}"
                                rows="4"
                            >${value}</textarea>

                        </div>

                    `;

                }


                /* NORMAL INPUT */

                return `

                    <div>

                        <label>
                            ${label}
                        </label>

                        <input

                            name="${key}"

                            type="${getInputType(
                                key
                            )}"

                            value="${value}"

                        >

                    </div>

                `;

            }
        )
        .join("");


    /* =================================================
       INSERT FORM
    ================================================= */

    /*
       ADDED/FIXED:
       Form is inserted directly into modalBody.
       There is NO .form-grid wrapper.
    */

    modalBody.innerHTML = `

        <h2>

            ${item.id
                ? "Edit"
                : "Add"}

            ${pages[page][0]}

        </h2>


        <form
            id="form"
            class="form"
        >

            ${generatedFields}


            <div class="full form-actions">

                <button
                    type="button"
                    onclick="closeModal()"
                >
                    Cancel
                </button>


                <button
                    type="submit"
                    class="primary"
                >
                    ${item.id
                        ? "Update"
                        : "Save"}
                </button>

            </div>

        </form>

    `;


    /*
       ADDED/FIXED:
       Show modal only AFTER form HTML
       has been inserted.
    */

    modal.classList.remove(
        "hidden"
    );


    /* =================================================
       FORM SUBMIT
    ================================================= */

    const form =
        document.getElementById(
            "form"
        );


    if (!form) {

        console.error(
            "Form was not created."
        );

        return;

    }


    form.onsubmit =
        async function(event) {

            event.preventDefault();


            try {

                let obj =
                    Object.fromEntries(
                        new FormData(form)
                    );


                /* Empty strings become null */

                for (
                    const key in obj
                ) {

                    if (
                        obj[key] === ""
                    ) {

                        obj[key] = null;

                    }

                }


                const method =
                    item.id
                        ? "PUT"
                        : "POST";


                const url =
                    API +
                    "/" +
                    endpoint[page] +
                    (
                        item.id
                            ? "/" + item.id
                            : ""
                    );


                console.log(
                    "Saving:",
                    method,
                    url,
                    obj
                );


                const response =
                    await fetch(
                        url,
                        {

                            method: method,

                            headers: {
                                "Content-Type":
                                    "application/json"
                            },

                            body:
                                JSON.stringify(
                                    obj
                                )

                        }
                    );


                if (
                    !response.ok
                ) {

                    const errorText =
                        await response.text();


                    console.error(
                        "Save failed:",
                        errorText
                    );


                    alert(
                        "Save failed. " +
                        "Check Spring Boot console."
                    );


                    return;

                }


                closeModal();


                loadCrud(
                    page
                );

            }


            catch (error) {

                console.error(
                    "Save error:",
                    error
                );


                alert(
                    "An error occurred " +
                    "while saving."
                );

            }

        };

}


/* =====================================================
   EDIT ITEM
===================================================== */

function editItem(item) {

    openForm(
        window.currentPage,
        item
    );

}


/* =====================================================
   DELETE ITEM
===================================================== */

async function deleteItem(id) {

    if (
        !confirm(
            "Delete this record?"
        )
    ) {

        return;

    }


    try {

        const response =
            await fetch(

                API +
                "/" +
                endpoint[
                    window.currentPage
                ] +
                "/" +
                id,

                {
                    method: "DELETE"
                }

            );


        if (
            !response.ok
        ) {

            alert(
                "Delete failed."
            );

            return;

        }


        loadCrud(
            window.currentPage
        );

    }


    catch (error) {

        console.error(
            error
        );


        alert(
            "Delete failed."
        );

    }

}


/* =====================================================
   CLOSE MODAL
===================================================== */

function closeModal() {

    const modal =
        document.getElementById(
            "modal"
        );


    /*
       ADDED/FIXED:
       Properly hide modal.
    */

    if (modal) {

        modal.classList.add(
            "hidden"
        );

    }

}


/* =====================================================
   ERROR
===================================================== */

function showError(error) {

    console.error(
        error
    );


    document.getElementById(
        "content"
    ).innerHTML = `

        <div class="section">

            <b>
                Unable to load data.
            </b>

            <p class="muted">

                Check that Spring Boot
                and MySQL are running.

            </p>

        </div>

    `;

}


/* =====================================================
   CHATBOT
===================================================== */

function openChat() {

    document
        .getElementById(
            "chat"
        )
        .classList
        .toggle(
            "hidden"
        );

}


async function sendChat() {

    const input =
        document.getElementById(
            "chatInput"
        );


    const message =
        input.value.trim();


    if (!message) {

        return;

    }


    const box =
        document.getElementById(
            "messages"
        );


    box.innerHTML += `

        <div class="user">

            ${safeValue(
                message
            )}

        </div>

    `;


    input.value = "";


    try {

        const response =
            await fetch(

                API + "/chatbot",

                {

                    method: "POST",

                    headers: {
                        "Content-Type":
                            "application/json"
                    },

                    body:
                        JSON.stringify({
                            message:
                                message
                        })

                }

            );


        if (
            !response.ok
        ) {

            throw new Error(
                "Chatbot request failed"
            );

        }


        const data =
            await response.json();


        box.innerHTML += `

            <div class="bot">

                ${data.reply ||
                    "No response"}

                <br>

                <small>

                    ${
                        (data.actions || [])
                        .join(" • ")
                    }

                </small>

            </div>

        `;

    }


    catch (error) {

        console.error(
            error
        );


        box.innerHTML += `

            <div class="bot">

                Sorry, I could not
                connect to the chatbot.

            </div>

        `;

    }


    box.scrollTop =
        box.scrollHeight;

}


/* =====================================================
   CHAT ENTER KEY
===================================================== */

document
    .getElementById(
        "chatInput"
    )
    .addEventListener(
        "keydown",
        event => {

            if (
                event.key === "Enter"
            ) {

                sendChat();

            }

        }
    );


/* =====================================================
   CLOCK
===================================================== */

setInterval(
    () => {

        document.getElementById(
            "clock"
        ).textContent =
            new Date()
                .toLocaleString(
                    "en-IN"
                );

    },
    1000
);


/* =====================================================
   START APPLICATION
===================================================== */

loadDashboard();