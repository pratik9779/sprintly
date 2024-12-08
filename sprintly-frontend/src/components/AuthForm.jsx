/* eslint-disable react/prop-types */
import { Button } from "@/components/ui/button";
import {
    Form,
    FormControl,
    FormDescription,
    FormField,
    FormItem,
    FormLabel,
    FormMessage,
} from "@/components/ui/form";
import { Input } from "@/components/ui/input";
import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import { z } from "zod";

const formSchema = z.object({
    emailID: z.string().email(),
    // username: z.string().min(3).max(50),
    password: z.string().min(8),
});

const AuthForm = ({ formtype, registerUser = {}, loginUser = {} }) => {
    const form = useForm({
        resolver: zodResolver(formSchema),
        defaultValues: {
            emailID: "",
            // username: "",
            password: "",
        },
    });

    const onSubmit = (values) => {
        formtype === "Register" ? registerUser(values) : loginUser(values)
    };

    return (
        <Form {...form}>
            <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-4">
                <AuthFormField
                    name="emailID"
                    label="Email"
                    placeholder="Email"
                    inputType="emailID"
                    formControl={form.control}
                />
                {/* <AuthFormField
                    name="username"
                    label="Username"
                    placeholder="Username"
                    description="At least 3 characters."
                    formControl={form.control}
                /> */}
                <AuthFormField
                    name="password"
                    label="Password"
                    placeholder="Password"
                    description="At least 8 characters."
                    inputType="password"
                    formControl={form.control}
                />
                <Button type="submit">{formtype}</Button>
            </form>
        </Form>
    );
};

const AuthFormField = ({
    name,
    label,
    placeholder,
    description,
    inputType,
    formControl,
}) => {
    return (
        <FormField
            control={formControl}
            name={name}
            render={({ field }) => (
                <FormItem>
                    <FormLabel>{label}</FormLabel>
                    <FormControl>
                        <Input
                            placeholder={placeholder}
                            type={inputType || "text"}
                            {...field}
                        />
                    </FormControl>
                    {description && <FormDescription>{description}</FormDescription>}
                    <FormMessage />
                </FormItem>
            )}
        />
    );
};

export default AuthForm;