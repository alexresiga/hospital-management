import { Injectable } from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router} from '@angular/router';
import { Observable } from 'rxjs';
import {select, Store} from '@ngrx/store';
import {State} from '../store';
import {GetCurrentUser} from '../../modules/auth/shared/state/user.actions';
import {filter, map, take} from 'rxjs/operators';
import {selectCurrentUser} from '../../modules/auth/shared/state/user.reducer';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardGuard implements CanActivate {
  constructor(private store: Store<State>,
              private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot,
              state: RouterStateSnapshot
  ): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    this.store.dispatch(new GetCurrentUser());
    return this.store.pipe(
      select(selectCurrentUser),
      filter(user => !!user),
      map(user => {
        if (route.data) {
          if (!user.roles.includes(route.data.roles)) {
            this.router.navigateByUrl('');
            return false;
          }
        }
        return true;
      }),
      take(1)
    );

  }

}
