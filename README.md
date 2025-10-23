# Fraud Detection System

A Java application that detects fraudulent orders by identifying multiple orders placed to the same address using different email addresses within a 24-hour time window.

## Features

- **Address Normalization**: Intelligently normalizes addresses to detect variations of the same location
  - Handles common abbreviations (Street → St, Avenue → Ave, Road → Rd, Drive → Dr)
  - Normalizes apartment/unit/suite designations
  - Handles NYC variations (NYC → New York City)
  - Case-insensitive comparison
  
- **Real-time Fraud Detection**: Analyzes order streams to identify suspicious patterns
  - Detects different email addresses shipping to the same normalized address
  - 24-hour sliding time window for fraud detection
  - Generates alerts for suspicious orders

## Requirements

- Java 8 or higher
- No external dependencies required

## Project Structure

```
fraud-detection-system/
├── src/
│   └── Main.java          # Main application with fraud detection logic
└── README.md
```

## How to Run

### Compile
```bash
javac src/Main.java
```

### Run
```bash
java -cp src Main
```

## How It Works

1. **Order Processing**: The system receives orders containing:
   - Order ID
   - Email address
   - Shipping address
   - Timestamp

2. **Address Normalization**: Each address is normalized using pattern matching and string transformations to handle variations like:
   - "123 Main Street, Apt 4B, NYC"
   - "123 Main St, Apartment 4B, NYC"
   - "123 Main Street Apt 4-B New York City"

3. **Fraud Detection**: The system checks if:
   - Multiple orders are placed to the same normalized address
   - Orders have different email addresses
   - Orders occur within 24 hours of each other

4. **Alerts**: When fraud is detected, the system generates alerts linking suspicious orders.

## Example Output

```
FRAUD ALERTS:
FRAUD ALERT: ORD003 - Matches previous order ORD001 (different email, same address)
FRAUD ALERT: ORD005 - Matches previous order ORD001 (different email, same address)
```

## Configuration

You can modify the time window for fraud detection by changing the `TIME_WINDOW_HOURS` constant in the `Main` class (default: 24 hours).

## Code Structure

### Classes

- **Order**: Represents an incoming order with ID, email, address, and timestamp
- **OrderInfo**: Lightweight order information stored for comparison
- **Main**: Contains the fraud detection logic and address normalization

### Key Methods

- `normalizeAddress(String address)`: Normalizes address strings for comparison
- `detectFraud(List<Order> orders)`: Analyzes orders and returns fraud alerts
- `processOrdersInRealTime(List<Order> stream)`: Processes order stream and prints alerts

## License

This project is for educational purposes.
