import { Pipe, PipeTransform } from '@angular/core';
import { Requests } from '../beans/requests';

@Pipe({
  name: 'filterByStatus',
})
export class FilterByStatusPipe implements PipeTransform {

  transform(requests: Array<Requests>, status = 0): any {
    if (status === 0) {
      return requests;
    } else {
      return requests.filter(request => request.status_id === status);
    }

  }

}
