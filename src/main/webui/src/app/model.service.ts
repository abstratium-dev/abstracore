import { Injectable, signal, Signal } from '@angular/core';

export interface Demo {
  id: string;
}

export interface Config {
  logLevel: string;
}

@Injectable({
  providedIn: 'root',
})
export class ModelService {

  private demos = signal<Demo[]>([]);
  private demosLoading = signal<boolean>(false);
  private demosError = signal<string | null>(null);
  private config = signal<Config | null>(null);

  demos$: Signal<Demo[]> = this.demos.asReadonly();
  demosLoading$: Signal<boolean> = this.demosLoading.asReadonly();
  demosError$: Signal<string | null> = this.demosError.asReadonly();
  config$: Signal<Config | null> = this.config.asReadonly();

  setDemos(demos: Demo[]) {
    this.demos.set(demos);
  }

  setDemosLoading(loading: boolean) {
    this.demosLoading.set(loading);
  }

  setDemosError(error: string | null) {
    this.demosError.set(error);
  }

  setConfig(config: Config) {
    this.config.set(config);
  }
}
