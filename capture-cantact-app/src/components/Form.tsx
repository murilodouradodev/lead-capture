import { useState } from "react";
import { TextField, MenuItem, Button, Box, Typography, Snackbar, Alert } from "@mui/material";
import axios from "axios";

const Form = () => {

    const [formData, setFormData] = useState({ name: "", email: "", phone: "", source: "" });
    const [openSnackbar, setOpenSnackbar] = useState(false);

    const handleChange = (event: React.ChangeEvent<HTMLInputElement | { name?: string; value: unknown }>) => {
        const { name, value } = event.target;
        setFormData(prevState => ({ ...prevState, [name as string]: value }));
    };

    const handleSubmit = (event: React.FormEvent) => {
        event.preventDefault();
        console.log(formData);
        axios.post("http://localhost:8080/v1/contact", formData).then(response => {
            try {
                console.log("Form submitted:", response.data);
                setOpenSnackbar(true);
            } catch (error) {
                console.error("Error submitting form:", error);
            }
            });

        console.log("Form submitted:", formData);
    };

    return (
        <Box sx={{ maxWidth: 600, mx: "auto", p: 3, border: "1px solid #ddd", borderRadius: 2 }}>
            <Typography variant="h5" gutterBottom>Contact Capture</Typography>
            <form onSubmit={handleSubmit}>
                <TextField
                    fullWidth
                    label="Nome"
                    name="name"
                    value={formData.name}
                    onChange={handleChange}
                    required
                    margin="normal"
                />
                <TextField
                    fullWidth
                    label="Email"
                    name="email"
                    type="email"
                    value={formData.email}
                    onChange={handleChange}
                    required
                    margin="normal"
                />

                <TextField
                    fullWidth
                    label="Telefone"
                    name="phone"
                    value={formData.phone}
                    onChange={handleChange}
                    required
                    margin="normal"
                />
                <TextField
                    select
                    id={"source"}
                    fullWidth
                    label="Como nos conheceu?"
                    name="source"
                    value={formData.source}
                    onChange={handleChange}
                    required
                    margin="normal"
                >
                    <MenuItem value="Test1">Test1</MenuItem>
                    <MenuItem value="Test2">Test2</MenuItem>
                </TextField>
                <Button type="submit" variant="contained" color="primary" fullWidth sx={{ mt: 2 }}>
                    Enviar
                </Button>
            </form>

            <Snackbar open={openSnackbar} autoHideDuration={3000} onClose={() => setOpenSnackbar(false)}>
                <Alert severity="success" onClose={() => setOpenSnackbar(false)}>
                    Contato enviado com sucesso!
                </Alert>
            </Snackbar>
        </Box>
    );
};

export default Form;
