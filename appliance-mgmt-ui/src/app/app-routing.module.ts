import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';

import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { RegisterComponent } from './pages/register/register.component';
import { AppliancesComponent } from './pages/appliances/appliances.component';
import { ReservationComponent } from './pages/reservation/reservation.component';
import { NotFoundComponent } from './pages/not-found/not-found.component';
import { AppComponent } from './app.component';

export const routes: Routes = [{
  path:'',
  children: [
    {
      path: 'dashboard',
      component: DashboardComponent,
    },
    {
      path: '',
      redirectTo: 'dashboard',
      pathMatch: 'full',
    },
    {
      path: 'new',
      component: RegisterComponent,
      pathMatch: 'full',
    },
    {
      path: 'all',
      component: AppliancesComponent,
      pathMatch: 'full',
    },
    // {
    //   path: 'reservation',
    //   component: ReservationComponent,
    //   pathMatch: 'full',
    // },
    {
      path: '**',
      component: NotFoundComponent,
    },
  ],
}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {
}
