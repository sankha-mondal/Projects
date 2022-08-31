import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DiscountComponent } from './Pages/discount/discount.component';
import { EmailComponent } from './Pages/email/email.component';
import { HomeComponent } from './Pages/home/home.component';
import { LoginComponent } from './Pages/login/login.component';
import { OrdersComponent } from './Pages/orders/orders.component';
import { ProductsComponent } from './Pages/products/products.component';
import { ProfileComponent } from './Pages/profile/profile.component';
import { RegisterComponent } from './Pages/register/register.component';
import { UpdateComponent } from './Pages/update/update.component';
import { UserComponent } from './Pages/user/user.component';

const routes: Routes = [
  { path:'', component:HomeComponent},
  { path:'user', component:UserComponent},
  { path:'update', component:UpdateComponent},
  { path:'login', component:LoginComponent},
  { path:'register', component:RegisterComponent},
  { path:'profile', component:ProfileComponent},
  { path:'products', component:ProductsComponent},
  { path:'discount', component:DiscountComponent},
  { path:'orders', component:OrdersComponent},
  { path:'email', component:EmailComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
