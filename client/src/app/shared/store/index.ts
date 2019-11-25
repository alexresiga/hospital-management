import {
  ActionReducer,
  ActionReducerMap,
  createFeatureSelector,
  createSelector,
  MetaReducer
} from '@ngrx/store';
import { environment } from '../../../environments/environment';
import {UserState} from '../../modules/auth/shared/state/user.reducer';
import { reducer as userReducer } from '../../modules/auth/shared/state/user.reducer';


export interface State {
  userState: UserState;
}

export const reducers: ActionReducerMap<State> = {
  userState: userReducer
};


export const metaReducers: MetaReducer<State>[] = !environment.production ? [] : [];
