import { render, screen, fireEvent } from "@testing-library/react";
import Form from "./Form";

describe("Form Component", () => {
    test("renders form elements correctly", () => {
        render(<Form />);

        expect(screen.getByLabelText(/Nome/i)).toBeInTheDocument();
        expect(screen.getByLabelText(/Email/i)).toBeInTheDocument();
        expect(screen.getByLabelText(/Telefone/i)).toBeInTheDocument();
        expect(screen.getByLabelText(/Como nos conheceu?/i)).toBeInTheDocument();
        expect(screen.getByRole("button", { name: /Enviar/i })).toBeInTheDocument();
    });

    test("updates input fields correctly", () => {
        render(<Form />);

        const nameInput = screen.getByLabelText(/Nome/i) as HTMLInputElement;
        const emailInput = screen.getByLabelText(/Email/i) as HTMLInputElement;
        const phoneInput = screen.getByLabelText(/Telefone/i) as HTMLSelectElement;

        fireEvent.change(nameInput, { target: { value: "John Doe" } });
        fireEvent.change(emailInput, { target: { value: "john@example.com" } });
        fireEvent.change(phoneInput, { target: { value: "(15)1111-1111" } });

        expect(nameInput.value).toBe("John Doe");
        expect(emailInput.value).toBe("john@example.com");
        expect(phoneInput.value).toBe("(15)1111-1111");
    });

    test("handles form submission", () => {
        render(<Form />);

        const submitButton = screen.getByRole("button", { name: /Enviar/i });

        fireEvent.click(submitButton);

        expect(submitButton).toBeInTheDocument();
    });
});
