import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserComponent } from './Pages/user/user.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UpdateComponent } from './Pages/update/update.component';
import { HomeComponent } from './Pages/home/home.component';
import { LoginComponent } from './Pages/login/login.component';
import { EmailComponent } from './Pages/email/email.component';
import { RegisterComponent } from './Pages/register/register.component';
import { NavbarComponent } from './Parts/navbar/navbar.component';
import { ProfileComponent } from './Pages/profile/profile.component';
import { ProductsComponent } from './Pages/products/products.component';
import { DiscountComponent } from './Pages/discount/discount.component';
import { OrdersComponent } from './Pages/orders/orders.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { NgxPaginationModule } from 'ngx-pagination';

import { MatSliderModule } from '@angular/material/slider';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatCardModule } from '@angular/material/card';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSelectModule } from '@angular/material/select';
import { MatIconModule } from '@angular/material/icon';


@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    UpdateComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    NavbarComponent,
    ProfileComponent,
    ProductsComponent,
    DiscountComponent,
    OrdersComponent,
    EmailComponent
  ],
  imports: [
    BrowserModule, AppRoutingModule, BrowserAnimationsModule, 
    HttpClientModule, FormsModule, ReactiveFormsModule, Ng2SearchPipeModule, NgxPaginationModule,
    MatSliderModule,MatInputModule, MatFormFieldModule, MatButtonModule, MatSnackBarModule, MatProgressBarModule,
    MatCardModule, MatProgressSpinnerModule, MatPaginatorModule, MatSelectModule, MatIconModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
