import { Routes } from "@angular/router";
import { Component } from "@angular/core";
import { CadastroComponent } from "./cadastro/cadastro.component";
import { LoginComponent } from "./login/login.component";

export const ROUTES: Routes = [
    {path: '', component: LoginComponent},
    {path: 'register', component: CadastroComponent}
]