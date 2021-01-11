/**
 * @license
 * Copyright Akveo. All Rights Reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */
import { Component, OnInit } from '@angular/core';

import { MENU_ITEMS } from './pages-menu';

@Component({
  selector: 'ngx-app',
  styleUrls: ['app.component.scss'],
  // template: `<router-outlet></router-outlet>`,
template: `<ngx-one-column-layout>
<nb-menu [items]="menu"></nb-menu>
<router-outlet></router-outlet>
</ngx-one-column-layout>
`,
})
export class AppComponent implements OnInit {

  menu = MENU_ITEMS;

  constructor() {
  }

  ngOnInit(): void {
  }
}
