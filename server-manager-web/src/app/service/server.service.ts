import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { catchError, Observable, tap, throwError } from "rxjs";
import { CustomResponse } from "../interface/custom-response";
import { Server } from "../interface/server";

@Injectable({
    providedIn: 'root'
})
export class ServerService{

    private readonly apiUrl = 'any';

    constructor(private http: HttpClient) {}

    /*getServers(): Observable<CustomResponse> {
        return this.http.get<CustomResponse>('http://localhost:8080/server/list');
    }*/

    servers$ = <Observable<CustomResponse>> this.http.get<CustomResponse>(`${this.apiUrl}/server/list`)
    .pipe(
        tap(console.log),
        catchError(this.handleError)
    );

    save$ = (server: Server) => <Observable<CustomResponse>> this.http.post<CustomResponse>(`${this.apiUrl}/server/save`, server)
    .pipe(
        tap(console.log),
        catchError(this.handleError)
    );

    handleError(handleError: any): Observable<never> {
        return throwError ('Method not implemented.')
    }


}